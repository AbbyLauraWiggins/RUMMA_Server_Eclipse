package Database.Repo;

import Database.Schema.Team;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TeamRepo {
    private Team team;
    private String whereClause = "";

    public TeamRepo(){

        team = new Team();

    }


    public static String createTable(){
        return "CREATE TABLE " + Team.TABLE  + "("
                + Team.KEY_TeamId  + "   PRIMARY KEY,"
                + Team.KEY_TeamName + " TEXT,"
                + Team.KEY_TeamLocation + " TEXT,"
                + Team.KEY_TeamCurPoints + " TEXT)";
    }


    public int insert(Team team) {
        return 1;
    }



    public void delete( ) {
        
    }

}
