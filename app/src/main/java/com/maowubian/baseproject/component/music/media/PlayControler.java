package com.maowubian.baseproject.component.music.media;


import android.view.View;

import com.maowubian.baseproject.component.music.media.data.MusicInfo;
import com.maowubian.baseproject.component.music.media.data.MusicStatus;
import com.maowubian.baseproject.databinding.MusicItemBinding;
import com.orhanobut.logger.Logger;

/**
 * Created by zhuruqiao on 16/8/5.
 */
public class PlayControler {

    private static PlayControler instance;

    private MediaConn conn;

    private MusicInfo info;

    private MusicItemBinding currentPlayItem;


    private PlayControler(MediaConn conn) {
        this.conn = conn;
    }

    public void start(View view) {
        info = (MusicInfo) view.getTag();
        Logger.i("播放歌曲:%s", info.name);
        conn.control.play(info.path);
        if (currentPlayItem!=null){
            currentPlayItem.musicProgress.setVisibility(View.INVISIBLE);
        }
        currentPlayItem = info.binding;
        currentPlayItem.musicProgress.setVisibility(View.VISIBLE);
        currentPlayItem.setStatus(new MusicStatus());

    }

    public static void init(MediaConn conn) {
        if (instance == null) {
            synchronized (PlayControler.class) {
                if (instance == null) {
                    instance = new PlayControler(conn);
                }
            }
        }

    }

    public static PlayControler getInstance() {
        if (instance == null) {
            Logger.e("PlayControler must be init");
        }
        return instance;
    }

    public MusicInfo getCurrentPlayMusic() {

        return info;
    }


}
