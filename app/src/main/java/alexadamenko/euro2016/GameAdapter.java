package alexadamenko.euro2016;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import alexadamenko.euro2016.DB.DBHelper;
import alexadamenko.euro2016.DB.Models.Game;
import alexadamenko.euro2016.IntentServices.SubcriptionService;

/**
 * Created by AlexAdamenko on 3/11/2016.
 */
public class GameAdapter  extends BaseAdapter {

    private static final String PREFS_NAME = "EURO_PREFS";

    Context context;
    List<Game> listData;
    DBHelper dbHelper;

    public GameAdapter(Context context,List<Game> listData){
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {
        private TextView firstTeam;
        private TextView secondTeam;
        private TextView gameDate;
    }
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.game_item,null);
            viewHolder = new ViewHolder();
            viewHolder.firstTeam = (TextView) view.findViewById(R.id.firstTeam);
            viewHolder.secondTeam = (TextView) view.findViewById(R.id.secondTeam);
            viewHolder.gameDate = (TextView) view.findViewById(R.id.date);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        dbHelper = new DBHelper(context);
        final Button sub_btn = (Button)view.findViewById(R.id.sub_btn);
        final TextView subbed = (TextView)view.findViewById(R.id.subbed);
        Game game = listData.get(position);
        if(game.getStage().equals("1")){
            sub_btn.setVisibility(View.INVISIBLE);
        }
        final String firstTeam = game.getFirst_player();
        final String secondTeam = game.getSecond_player();
        final String gameDate = game.getDate();
        viewHolder.firstTeam.setText(firstTeam);
        viewHolder.secondTeam.setText(secondTeam);
        viewHolder.gameDate.setText(gameDate);

        sub_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Game game = listData.get(position);
                SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE).edit();
                editor.putString("game", game.getGame_id());//or some other task
                editor.commit();
                dbHelper.updateValue(game.getGame_id(), "1");
                sub_btn.setVisibility(View.INVISIBLE);
                final SubcriptionService subService = new SubcriptionService(context);
                try {
                    subService.subscribe();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        });

        return view;
    }
}

