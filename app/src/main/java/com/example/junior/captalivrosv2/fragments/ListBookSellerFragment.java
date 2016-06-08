package com.example.junior.captalivrosv2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.junior.captalivrosv2.R;
import com.example.junior.captalivrosv2.adapter.BookAdapter;
import com.example.junior.captalivrosv2.dao.SessionManager;
import com.example.junior.captalivrosv2.domain.Book;
import com.example.junior.captalivrosv2.domain.Seller;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListBookSellerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListBookSellerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListBookSellerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView vazio;
    private List<Book> mList;
    private RecyclerView mRecyclerView;
    private SessionManager session;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListBookSellerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListBookSellerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListBookSellerFragment newInstance(String param1, String param2) {
        ListBookSellerFragment fragment = new ListBookSellerFragment();
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
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_list_book_seller, container, false);
        vazio=(TextView) v.findViewById(R.id.vazio);

        session=new SessionManager(getActivity());

        mRecyclerView=(RecyclerView) v.findViewById(R.id.recycler_book_seller);
        mRecyclerView.setHasFixedSize(true);
        Seller seller=new Seller();

        mList=Book.findWithQuery(Book.class,"Select * from Book where user=?",String.valueOf(session.getUserDetails().getId()));
        if(mList.isEmpty()){
            vazio.setText("Lista Vazia");
        }

        BookAdapter adapter=new BookAdapter(mList,getActivity());
        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(adapter);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
