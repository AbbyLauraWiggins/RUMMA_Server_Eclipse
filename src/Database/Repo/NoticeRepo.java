package Database.Repo;

import Database.Schema.Notice;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class NoticeRepo {

    private Notice notice;
    String whereClause = "";

    public NoticeRepo(){
        notice = new Notice();
    }

    public static String createTable() {
        return "CREATE TABLE " + Notice.TABLE + "("
                + Notice.KEY_NoticeId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Notice.KEY_MemberId + " TEXT," //FOREIGN KEY FROM MEMBERID
                + Notice.KEY_Contents + " TEXT,"
                + Notice.KEY_Date + " TEXT)";
    }

    public void insert(Notice notice) {

    }

    public void delete() {

    }


}
