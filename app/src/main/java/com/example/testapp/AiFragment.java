package com.example.testapp;


import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapp.api.ImagePostRequest;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.UtilityClass;
import com.github.drjacky.imagepicker.ImagePicker;
import com.github.drjacky.imagepicker.constant.ImageProvider;

import org.jetbrains.annotations.NotNull;


import java.io.IOException;


import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TextView textView;

    public ImageView slika;

    public TextView pesticidPreporuka;



    public AiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlanerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AiFragment newInstance(String param1, String param2) {
        AiFragment fragment = new AiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ai, container, false);
        slika = view.findViewById(R.id.imageView);
        pesticidPreporuka = view.findViewById(R.id.textView3);

        ActivityResultLauncher<Intent> launcher=
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
                    if(result.getResultCode()==RESULT_OK){
                        Uri uri=result.getData().getData();
                        slika.setImageURI(uri);

                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        String path= MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "slika" , "slika");
                        Uri newUri=Uri.parse(path);
                        String[] projection = { MediaStore.Images.Media.DATA };
                        Cursor cursor = getActivity().getContentResolver().query(newUri, projection, null, null, null);
                        cursor.moveToFirst();
                        String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        cursor.close();


                        sendImage(imagePath);

                        // Use the uri to load the image
                    }else if(result.getResultCode()==ImagePicker.RESULT_ERROR){
                        // Use ImagePicker.Companion.getError(result.getData()) to show an error
                        System.out.println(ImagePicker.Companion.getError(result.getData()));
                    }
                });
        ImagePicker.Companion.with(getActivity())
                .maxResultSize(1920,1080,true)
                .provider(ImageProvider.BOTH)
                .crop()
                .createIntentFromDialog((Function1)(new Function1(){
                    public Object invoke(Object var1){
                        this.invoke((Intent)var1);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@NotNull Intent it){
                        Intrinsics.checkNotNullParameter(it,"it");
                        launcher.launch(it);
                    }
                }));


        textView = view.findViewById(R.id.textView);





        return view;
    }

    public void sendImage(String uri)
    {


        if(getActivity() != null) {
            ImagePostRequest.sendImagePostRequest(getActivity(), uri);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    textView.setText(MainActivity2.responseString);
                    Pesticid pesticid = UtilityClass.preporukaPesticid(textView.getText().toString());
                    if(pesticid !=null)
                    {
                        pesticidPreporuka.setText(pesticid.getNaziv());
                    }
                    else
                        pesticidPreporuka.setText("Nije pronađen pesticid za tu bolest.");


                }
            }, 1000L);


            MainActivity2.responseString=null;


        }
        else
            System.out.println("getActivity je null");
    }




}