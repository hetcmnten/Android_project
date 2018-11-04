package com.teemo.xuantruong.android_project.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.CustomItemClickListener;
import com.teemo.xuantruong.android_project.adapters.RecyclerViewAdapter;
import com.teemo.xuantruong.android_project.adapters.RecyclerViewSourceAdapter;
import com.teemo.xuantruong.android_project.entity.Category;
import com.teemo.xuantruong.android_project.entity.FlagCategorySource;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.entity.Source;
import com.teemo.xuantruong.android_project.poster.Poster;

import java.util.ArrayList;

public class FragmentSources extends Fragment implements View.OnClickListener {
    View view;
    public ArrayList<Source> listSource;
    public FragmentSources() {

        SetDataCategories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_source, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_source);
        RecyclerViewSourceAdapter recyclerViewAdapter = new RecyclerViewSourceAdapter(getActivity(), listSource, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FlagCategorySource.flagSource = position;
                FlagCategorySource.flaCategory = 0;
              Toast.makeText(getActivity(), "Source" + position, Toast.LENGTH_LONG).show();
                ((FragmentHomePage)getParentFragment()).viewPager.setCurrentItem(0);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    public void SetDataCategories(){
        listSource = new ArrayList<Source>();
        ArrayList<Category> lisCategories = new ArrayList<>();
        ArrayList<Category> lisCategorie1 = new ArrayList<>();
        ArrayList<Category> lisCategorie2 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters1 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters2 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters3 = new ArrayList<>();

        //Fake data lisposters
        listPosters1.add(new Poster_entity(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước",
                "Trong số các nạn nhân bị thương, có 2 người bị thương nặng đang được " +
                "cấp cứu tại Bệnh viện Nhân dân Gia Định. Đứng bên ngoài phòng cấp cứu, bà Trần Mỹ Lệ (63 tuổi, quận Bình Thạnh) mắt đỏ hoe, chắp tay cầu nguyện cho con " +
                "bà là anh H.H.Đ.(42 tuổi) tai qua nạn khỏi. Theo bà Lệ, rạng sáng nay bà nhận được điện thoại thông báo anh Đ đang được cấp cứu tại Bệnh viện Nhân dân Gia " +
                "Định “Chạy đến bệnh viện thì y tá nói con tôi bị gãy chân, bị thương nặng ở cổ." +
                " Khi đó, tôi thấy nó nằm trên băng ca nhưng người lờ đờ lắm. Nó thì thào nói con không biết gì cả”, bà Lệ nói trong lo lắng."));
        listPosters1.add(new Poster_entity(R.drawable.news+"","Hôm nay tôi buồn-1 ","11h Trước","abc"));
        listPosters1.add(new Poster_entity(R.drawable.news+"","Cuối tuần đi đâu chơi-1 ","11h Trước","mắt đỏ hoe, chắp tay cầu nguyện cho con"));
        listPosters1.add(new Poster_entity(R.drawable.news+"","Cao thủ liên minh giải nghệ-1 ","11h Trước","Bệnh viện Nhân dân Gia"));


        listPosters2.add(new Poster_entity(R.drawable.news+"","Còn điều gì chưa nói vs nhau-2 ","11h Trước","Người đàn ông nuôi hàng trăm con rồng"));
        listPosters2.add(new Poster_entity(R.drawable.news+"","Cách tán gái từ đại gia-2 ","11h Trước","bà là anh H.H.Đ.(42 tuổi) tai qua nạn khỏi. Theo bà Lệ,"));
        listPosters2.add(new Poster_entity(R.drawable.news+"","Bảo vệ đôi mắt của bạn-2 ","11h Trước","Đứng bên ngoài phòng cấp cứu, bà Trần Mỹ Lệ"));
        listPosters2.add(new Poster_entity(R.drawable.news+"","Bao giờ em mới cưới-2","11h Trước","63 tuổi, quận Bình Thạnh"));

        listPosters3.add(new Poster_entity(R.drawable.news+"","Ánh sang phía sau còn đường đen-3","11h Trước","Theo bà Lệ, rạng sáng nay bà nhận được điện thoại thông báo anh Đ đang được cấp cứu tại Bệnh viện Nhân dân Gia"));
        listPosters3.add(new Poster_entity(R.drawable.news+"","Lee sin best mù-3","11h Trước","Theo bà Lệ, rạng sáng nay bà nhận được điện thoại thông báo anh Đ đang được cấp cứu tại Bệnh viện Nhân dân Gia"));

        // Fake data catogeries
        lisCategories.add(new Category("Tin Hot","bcd",listPosters1));
        lisCategories.add(new Category("Kinh Tế","bcd",listPosters2));
        lisCategories.add(new Category("Nhân Văn","bcd",listPosters3));

        lisCategorie1.add(new Category("Sức Khỏe","bcd",listPosters1));
        lisCategorie1.add(new Category("Tình dục","bcd",listPosters2));
        lisCategorie1.add(new Category("Đời sống","bcd",listPosters3));


        lisCategorie2.add(new Category("Xe hơi","bcd",listPosters1));
        lisCategorie2.add(new Category("Thời sự","bcd",listPosters2));
        lisCategorie2.add(new Category("Giải trí","bcd",listPosters3));


        //Fake data source
        listSource.add(new Source("CafeF","Abc",lisCategories));
        listSource.add(new Source("VietnamNet","Abc",lisCategorie1));
        listSource.add(new Source("Kenh14","Abc",lisCategorie2));
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(),"fragment source click",Toast.LENGTH_LONG).show();

    }
}