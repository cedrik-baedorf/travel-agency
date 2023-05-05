package travel.travelagency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import travelagency.api.DbService;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DbServiceTest {

    private DbService dbService;

    @BeforeEach
    public void setUp() {
        dbService = new DbService();
    }

    @Test
    public void testConnectToDB() throws SQLException {
        dbService.connectToDB();
        Connection connection = dbService.getConnection();
        assertNotNull(connection);
    }
}
