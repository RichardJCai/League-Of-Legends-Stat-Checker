/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package league.stat.checker;

import java.io.IOException;

/**
 * League of Legends Info Checker v1
 * @author Richard Cai
 * Date: 01/29/2017
 * Main code
 */

public class LeagueStatChecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Interface application = new Interface();
        application.setDefaultCloseOperation( Interface.EXIT_ON_CLOSE );
        application.setSize( 500, 300 );
        application.setVisible( true );
    }
    
}
