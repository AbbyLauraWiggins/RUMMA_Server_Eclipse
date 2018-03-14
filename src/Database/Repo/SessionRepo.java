package Database.Repo;

import Database.Schema.Session;

/**
 * Created by abbylaura on 10/03/2018.
 */

public class SessionRepo {

    private Session session;
    private String whereclause = "";

    public SessionRepo(){
        session = new Session();
    }

    public static String createTable(){
        return "CREATE TABLE " + Session.TABLE + "("
                //+ Session.KEY_AUTO + " TEXT PRIMARY KEY,"
                + Session.KEY_MemberID + " TEXT,"
                + Session.KEY_SessionID + " TEXT,"
                + Session.KEY_Deadlifts + " TEXT,"
                + Session.KEY_DeadliftJumps + " TEXT,"
                + Session.KEY_BackSquat + " TEXT,"
                + Session.KEY_BackSquatJumps + " TEXT,"
                + Session.KEY_GobletSquat + " TEXT,"
                + Session.KEY_BenchPress + " TEXT,"
                + Session.KEY_MilitaryPress + " TEXT,"
                + Session.KEY_SupineRow + " TEXT,"
                + Session.KEY_ChinUps + " TEXT,"
                + Session.KEY_Trunk + " TEXT,"
                + Session.KEY_RDL + " TEXT,"
                + Session.KEY_SplitSquat + " TEXT,"
                + Session.KEY_FourWayArms + " TEXT)";

    }

    public int insert(Session session) {
        return 1;
    }

   
}
