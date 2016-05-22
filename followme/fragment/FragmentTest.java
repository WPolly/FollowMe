package com.xiaoshan.polly.followme.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoshan.polly.followme.CustomTextViewActivity;
import com.xiaoshan.polly.followme.DetailActivity;
import com.xiaoshan.polly.followme.DetailCollapseActivity;
import com.xiaoshan.polly.followme.GzipTestActivity;
import com.xiaoshan.polly.followme.LoadNetImageActivity;
import com.xiaoshan.polly.followme.LruCacheImgLoadActivity;
import com.xiaoshan.polly.followme.MVPTestActivity;
import com.xiaoshan.polly.followme.NdkTestActivity;
import com.xiaoshan.polly.followme.OpenNetVideoActivity;
import com.xiaoshan.polly.followme.PhotoViewTestActivity;
import com.xiaoshan.polly.followme.R;
import com.xiaoshan.polly.followme.ShowWaveActivity;
import com.xiaoshan.polly.followme.VPImitaterActivity;
import com.xiaoshan.polly.followme.VolleyTestActivity;
import com.xiaoshan.polly.followme.YouKuMenvActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentTest.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTest extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rvTest;
    private SwipeRefreshLayout srTest;
    private MyHolderAdapter mAdapter;
    private List<String> mStrs;

//    private OnFragmentInteractionListener mListener;

    public FragmentTest() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTest.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTest newInstance(String param1, String param2) {
        FragmentTest fragment = new FragmentTest();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListData();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rvTest = (RecyclerView) view.findViewById(R.id.rv_test);
        srTest = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_test);
        srTest.setColorSchemeColors(Color.BLACK, Color.GREEN, Color.BLUE, Color.YELLOW);
        srTest.setOnRefreshListener(this);

        mAdapter = new MyHolderAdapter();
        rvTest.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false);
        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rvTest.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("FragmentTest", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("FragmentTest", "onDestroy");
    }

    private void initListData() {
        mStrs = new ArrayList<>();
        for (int i=0; i < 50; i++) {
            String str = "this is item--";
            mStrs.add(str);
        }
    }


    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(0,1000);
        Toast.makeText(getContext(), "正在刷新", Toast.LENGTH_SHORT).show();

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String str = "the new one";
                    mStrs.add(0, str);
                    mAdapter.notifyDataSetChanged();
                    srTest.setRefreshing(false);
                    break;

                case 1:
                    break;
            }
        }
    };

    class MyHolderAdapter extends RecyclerView.Adapter {

        @Override
        public MyRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, parent, false);
//            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//            layoutParams.height = (int) (300*(Math.random()) + 60);
//            view.setLayoutParams(layoutParams);

            MyRecycleHolder holder = new MyRecycleHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((MyRecycleHolder)holder).textView.setText(mStrs.get(position) + position);
            ((MyRecycleHolder)holder).textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 4) {
                        Intent intent = new Intent(getContext(), DetailCollapseActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 5) {
                        Intent intent = new Intent(getContext(), DetailActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 6) {
                        Intent intent = new Intent(getContext(), LoadNetImageActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

//                    if (position == 7) {
//                        Intent intent = new Intent(getContext(),JniTstActivity.class);
//                        getContext().startActivity(intent);
//                        return;
//                    }

                    if (position == 8) {
                        Intent intent = new Intent(getContext(),YouKuMenvActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 9) {
                        Intent intent = new Intent(getContext(),VPImitaterActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 10) {
                        Intent intent = new Intent(getContext(),ShowWaveActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 11) {
                        Intent intent = new Intent(getContext(),CustomTextViewActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 12) {
                        Intent intent = new Intent(getContext(),VolleyTestActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 13) {
                        Intent intent = new Intent(getContext(),LruCacheImgLoadActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 14) {
                        Intent intent = new Intent(getContext(),MVPTestActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 15) {
                        Intent intent = new Intent(getContext(),GzipTestActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 16) {
                        Intent intent = new Intent(getContext(),NdkTestActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 17) {
                        Intent intent = new Intent(getContext(),OpenNetVideoActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }

                    if (position == 18) {
                        Intent intent = new Intent(getContext(),PhotoViewTestActivity.class);
                        getContext().startActivity(intent);
                        return;
                    }
                    Toast.makeText(getContext(),"我是条目"+position+"我被点击了",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mStrs.size();
        }
    }

    class MyRecycleHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyRecycleHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.tv_test);
        }
    }

    //    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
