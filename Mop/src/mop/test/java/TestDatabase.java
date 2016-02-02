package mop.test.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.logging.log4j.Logger;
import static java.nio.charset.Charset.defaultCharset;

import mop.main.java.backend.configuration.AppConfig;
import mop.main.java.backend.configuration.ConfigurationManager;
import mop.main.java.backend.utilities.Log;
import mop.main.java.common.Constants;
import mop.main.java.common.exceptions.XmlException;
import mop.main.java.database.utilities.ConnectionProperties;
import mop.main.java.database.utilities.SqlConnectionFactory;

class TestDatabase {

    private static final Logger log = Log.getLog(TestDatabase.class);

    public static void main(String[] args) throws SQLException, XmlException {

        Connection connection = null;
        ScriptRunner scriptRunner;

        String[] scriptsToRun = {

            "src/Tests/Common/SqlScripts/CreateTestSchema.sql",                 // Database
            "src/Tests/Common/SqlScripts/TestDataPlaylist.sql",                 // Playlist
            "src/Tests/Common/SqlScripts/TestDataMovie.sql",                    // Movie
            "src/Tests/Common/SqlScripts/TestDataPlaylistMovie.sql",            // PlaylistMovie
            "src/Tests/Common/SqlScripts/TestDataRelatedMovie.sql",             // RelatedMovie
            "src/Tests/Common/SqlScripts/TestDataActor.sql",                    // Actor
            "src/Tests/Common/SqlScripts/TestDataMovieActor.sql",               // MovieActor
            "src/Tests/Common/SqlScripts/TestDataAcademyAwardWinner.sql",       // AcademyAwardWinner
            "src/Tests/Common/SqlScripts/TestDataGenre.sql",                    // Genre
            "src/Tests/Common/SqlScripts/TestDataMovieGenre.sql",               // MovieGenre
            "src/Tests/Common/SqlScripts/TestDataDocumentary.sql",              // Documentary
            "src/Tests/Common/SqlScripts/TestDataPlaylistDocumentary.sql",      // PlaylistDocumentary
            "src/Tests/Common/SqlScripts/TestDataDocumentaryEpisode.sql",       // DocumentaryEpisode
            "src/Tests/Common/SqlScripts/TestDataSeries.sql",                   // Series
            "src/Tests/Common/SqlScripts/TestDataPlaylistSeries.sql",           // PlaylistSeries
            "src/Tests/Common/SqlScripts/TestDataSeason.sql",                   // Season
            "src/Tests/Common/SqlScripts/TestDataSeasonEpisode.sql"             // Episode
        };

        ConfigurationManager configurationManager = new ConfigurationManager();
        AppConfig config = configurationManager.getAllValues(Constants.AppConfig.name);

        ConnectionProperties properties = new ConnectionProperties.Builder()
            .driverName(Constants.MySql.driver)
            .url(Constants.MySql.url)
            .userName(config.getUsername())
            .password(config.getPassword())
            .schema("TestMop")
            .build();

        try {

            connection  = new SqlConnectionFactory().openConnection(properties);
            scriptRunner = new ScriptRunner(connection);

            for(String scriptToRun : scriptsToRun) {

                try {

                    log.info("Running Test Data Script - " + scriptToRun.substring(scriptToRun.lastIndexOf("/") + 1));
                    Reader script = new BufferedReader(new InputStreamReader(new FileInputStream(scriptToRun), defaultCharset()));
                    scriptRunner.runScript(script);
                }
                catch (IOException ex) {

                    log.error("Unable to load and run script " + scriptToRun + ".", ex);
                }
            }
        }
        catch (ClassNotFoundException | SQLException ex) {

            log.error("Error opening connection to the database.  Unable to run mop.test scripts.", ex);
        }
        finally {

            if(connection != null) {

                connection.close();
            }
        }
    }
}
