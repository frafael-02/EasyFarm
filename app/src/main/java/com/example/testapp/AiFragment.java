package com.example.testapp;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.api.ChatAdapter;
import com.example.testapp.api.ImagePostRequest;
import com.example.testapp.api.VirtualAgent;
import com.example.testapp.entiteti.Message;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.UtilityClass;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.drjacky.imagepicker.ImagePicker;
import com.github.drjacky.imagepicker.constant.ImageProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
    public FloatingActionButton analizaBtn;
    ProgressBar progressBar;
    public EditText pitanje;
    public TextView odgovor;
    private static volatile String odgovorStatic;
    public Button pitajBtn;
    ShimmerFrameLayout shimmerFrameLayout;
    NestedScrollView scrollView;
    private ActivityResultLauncher<Intent> launcher;
    public long timer;

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private static List<Message> messages = new ArrayList<>();
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
        timer = 500L;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ai, container, false);
        slika = view.findViewById(R.id.imageView);
        pesticidPreporuka = view.findViewById(R.id.textView3);
        analizaBtn = view.findViewById(R.id.button4);
        progressBar=view.findViewById(R.id.progresBar);
        progressBar.setVisibility(View.INVISIBLE);
        ConstraintLayout constraintLayout=view.findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        pitanje = view.findViewById(R.id.pitanjeTextView);
       // odgovor=view.findViewById(R.id.odgovorText);
        pitajBtn = view.findViewById(R.id.button3);
        shimmerFrameLayout=view.findViewById(R.id.shimmer_view);
        shimmerFrameLayout.setVisibility(View.INVISIBLE);
        scrollView=view.findViewById(R.id.screenId);
        launcher=
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
                    if(result.getResultCode()==RESULT_OK){
                        progressBar.setVisibility(View.VISIBLE);
                        Uri uri=result.getData().getData();
                        slika.setImageURI(uri);
                        Animation anim = new ScaleAnimation(
                                0.5f, 1f, // Start and end values for the X axis scaling
                                0.5f, 1f, // Start and end values for the Y axis scaling
                                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                        anim.setFillAfter(true); // Needed to keep the result of the animation
                        anim.setDuration(700);
                        Animation anim2 = new ScaleAnimation(
                                1f, 0.85f, // Start and end values for the X axis scaling
                                1f, 0.85f, // Start and end values for the Y axis scaling
                                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                        anim2.setFillAfter(true); // Needed to keep the result of the animation
                        anim2.setDuration(300);
                        slika.setVisibility(View.VISIBLE);
                        slika.startAnimation(anim);
                        Runnable myThread = () ->
                        {
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            slika.startAnimation(anim2);
                        };
                        // Instantiating Thread class by passing Runnable
                        // reference to Thread constructor
                        Thread run = new Thread(myThread);
                        // Starting the thread
                        run.start();
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
        analizaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odaberiSliku();
            }
        });
        textView = view.findViewById(R.id.textView);
        pitajBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odgovorStatic=null;
                timer=500L;
                pitaj(pitanje.getText().toString());
                new Handler().postDelayed(new Runnable() {
                    public void run () {
                        while(odgovorStatic == null)
                        {}
                        if(odgovorStatic!=null)
                        {
                            messages.add(new Message(odgovorStatic, false));
                            chatAdapter.notifyItemInserted(messages.size() - 1);

                            // Clear the input field


                            // Scroll to the bottom of the RecyclerView
                            scrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                                }
                            });
                            //  odgovor.setText(odgovorStatic);
                        }

                    }
                }, timer);
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        chatAdapter = new ChatAdapter(messages);
        recyclerView.setAdapter(chatAdapter);
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
                    progressBar.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    pesticidPreporuka.setVisibility(View.VISIBLE);
                    Pesticid pesticid = UtilityClass.preporukaPesticid(textView.getText().toString());
                    if(pesticid !=null)
                    {
                        pesticidPreporuka.setText(pesticid.getNaziv());
                        pesticidPreporuka.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                klikNaPesticid(pesticid);
                            }
                        });
                       // pitanje.setText("Reci mi nešto o" + " " + pesticid.getNaziv());
                        pitaj("Reci mi nešto o" + " " + pesticid.getNaziv());
                        new Handler().postDelayed(new Runnable() {
                            public void run () {
                                while(odgovorStatic == null)
                                {}
                                if(odgovorStatic!=null)
                                {
                                    messages.add(new Message(odgovorStatic, false));
                                    chatAdapter.notifyItemInserted(messages.size() - 1);

                                    // Clear the input field


                                    // Scroll to the bottom of the RecyclerView
                                    recyclerView.scrollToPosition(messages.size() - 1);
                                    // odgovor.setText(odgovorStatic);
                                }

                            }
                        }, timer);
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
    public void odaberiSliku()
    {
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
    }
    public void pitaj(String pitanje)
    {
        messages.add(new Message(pitanje, true));
        chatAdapter.notifyItemInserted(messages.size() - 1);

        // Clear the input field


        // Scroll to the bottom of the RecyclerView


        List<String> result = new ArrayList<>();
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.INVISIBLE);
        shimmerFrameLayout.startShimmer();
        Handler handler = new Handler();
        handler.postDelayed(()->{
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.VISIBLE);
        },3000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                odgovorStatic = (VirtualAgent.chatGPT(pitanje));

            }
        }).start();



    }

    public void klikNaPesticid(Pesticid pesticid)
    {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("/pesticidiSlike/" + pesticid.getId() + ".jpg");
        String imageUrl = storageReference.toString();
        Intent intent = new Intent(getContext(), OdabraniPesticidActivity.class);
        intent.putExtra("PESTICID", (Serializable) pesticid);

        intent.putExtra("SLIKA", imageUrl);
        startActivity(intent);
    }

}