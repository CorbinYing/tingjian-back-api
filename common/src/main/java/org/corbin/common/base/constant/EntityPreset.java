package org.corbin.common.base.constant;


/**
 * 预设值
 */
public interface EntityPreset {

    /**
     * 性别
     */
    enum Sex {

        man("男", Preset.MAN),

        woman("女", Preset.WOMEN),

        secret("保密", Preset.UN_SEX);

        String sex;
        Integer sexEncoding;

        Sex(String sex, Integer sexEncoding) {
            this.sex = sex;
            this.sexEncoding = sexEncoding;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Integer getSexEncoding() {
            return sexEncoding;
        }

        public void setSexEncoding(Integer sexEncoding) {
            this.sexEncoding = sexEncoding;
        }
    }

    /**
     * 歌曲类型
     */
    enum SongType {
        other("其他", Preset.OTHER),
        classic("古典", Preset.CLASSICAL),
        rock("摇滚", Preset.ROCK_AND_ROLL),
        pop("流行", Preset.POP),
        chinese("华语", Preset.CHINESE),
        folk("民谣", Preset.FOLK),
        euorpe("欧美", Preset.EUROPE);


        String songType;
        Integer songTypeEncoding;

        SongType(String songType, Integer songTypeEncoding) {
            this.songTypeEncoding = songTypeEncoding;
            this.songType = songType;
        }

        public String getSongType() {
            return songType;
        }

        public void setSongType(String songType) {
            this.songType = songType;
        }

        public Integer getSongTypeEncoding() {
            return songTypeEncoding;
        }

        public void setSongTypeEncoding(Integer songTypeEncoding) {
            this.songTypeEncoding = songTypeEncoding;
        }
    }

    /**
     * 歌手类型
     */
    enum SingerType {
        other("其他", Preset.OTHER),
        classic("古典", Preset.CLASSICAL),
        rock("摇滚", Preset.ROCK_AND_ROLL),
        pop("流行", Preset.POP),
        chinese("华语", Preset.CHINESE),
        folk("民谣", Preset.FOLK),
        euorpe("欧美", Preset.EUROPE);


        String singerType;
        Integer singerTypeEncoding;


        SingerType(String singerType, Integer singerTypeEncoding) {
            this.singerType = singerType;
            this.singerTypeEncoding = singerTypeEncoding;
        }

        public String getSingerType() {
            return singerType;
        }

        public void setSingerType(String singerType) {
            this.singerType = singerType;
        }

        public Integer getSingerTypeEncoding() {
            return singerTypeEncoding;
        }

        public void setSingerTypeEncoding(Integer singerTypeEncoding) {
            this.singerTypeEncoding = singerTypeEncoding;
        }
    }

    /**
     * 收藏类型
     */
    enum CollectType {
        other("其他", Preset.OTHER),
        song("歌曲", Preset.SONG),
        singer("歌手", Preset.SINGER),
        song_order("歌单", Preset.SONG_ORDER),
        mv("MV",Preset.MV);

        String collectType;
        Integer collectTypeEncoding;

        CollectType(String collectType, Integer collectTypeEncoding) {
            this.collectType = collectType;
            this.collectTypeEncoding = collectTypeEncoding;
        }

        public String getCollectType() {
            return collectType;
        }

        public void setCollectType(String collectType) {
            this.collectType = collectType;
        }

        public Integer getCollectTypeEncoding() {
            return collectTypeEncoding;
        }

        public void setCollectTypeEncoding(Integer collectTypeEncoding) {
            this.collectTypeEncoding = collectTypeEncoding;
        }
    }

    interface Preset {
        Integer MAN = 1;
        Integer WOMEN = 2;
        Integer UN_SEX = 0;

        /**
         * 歌曲、歌手类型  0.其他 1 .古典、2.摇滚、3流行、4华语、5民谣、6欧美
         */
        Integer OTHER = 0;
        Integer CLASSICAL = 1;
        Integer ROCK_AND_ROLL = 2;
        Integer POP = 3;
        Integer CHINESE = 4;
        Integer FOLK = 5;
        Integer EUROPE = 6;


        /**
         * 收藏内容类型 0 其他 1歌曲 2 歌手 3 歌单 4 mv
         */
        Integer SONG = 1;
        Integer SINGER = 2;
        Integer SONG_ORDER = 3;
        Integer MV = 4;

    }

}
