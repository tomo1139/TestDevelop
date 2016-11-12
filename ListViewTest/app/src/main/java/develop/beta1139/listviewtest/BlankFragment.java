package develop.beta1139.listviewtest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BlankFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BlankFragment() {
    }

    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_blank, container, false);
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> upperArray = new ArrayList<>();
        for (Integer i=0; i<10; i++) {
            upperArray.add("upper " + i.toString());
        }
        RecyclerAdapter upperAdapter = new RecyclerAdapter(getActivity(), upperArray);
        RecyclerView mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.upper_recycler_view);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(upperAdapter);


        ArrayList<String> lowerArray = new ArrayList<>();
        for (Integer i=0; i<10; i++) {
            lowerArray.add("lower " + i.toString());
        }
        RecyclerAdapter lowerAdapter = new RecyclerAdapter(getActivity(), lowerArray);
        RecyclerView mRecyclerView2 = (RecyclerView) mRootView.findViewById(R.id.lower_recycler_view);
        mRecyclerView2.setNestedScrollingEnabled(false);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView2.setAdapter(lowerAdapter);
    }
}
