package dao;

import database.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import model.Bug;

public class BugDAO {

    public boolean addBug(Bug bug) {
        String sql = "INSERT INTO bugs(project_id, title, description, severity, status, reported_by, assigned_to) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bug.getProjectId());
            ps.setString(2, bug.getTitle());
            ps.setString(3, bug.getDescription());
            ps.setString(4, bug.getSeverity());
            ps.setString(5, bug.getStatus());
            ps.setInt(6, bug.getReportedBy());

            if (bug.getAssignedTo() == 0) {
                ps.setNull(7, Types.INTEGER);
            } else {
                ps.setInt(7, bug.getAssignedTo());
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Add bug error: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<Bug> getAllBugs() {
        ArrayList<Bug> bugs = new ArrayList<>();
        String sql = "SELECT * FROM bugs";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bug bug = new Bug(
                        rs.getInt("bug_id"),
                        rs.getInt("project_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("severity"),
                        rs.getString("status"),
                        rs.getInt("reported_by"),
                        rs.getInt("assigned_to")
                );

                bugs.add(bug);
            }

        } catch (SQLException e) {
            System.out.println("Get bugs error: " + e.getMessage());
        }

        return bugs;
    }

    public boolean updateBugStatus(int bugId, String status) {
        String sql = "UPDATE bugs SET status = ? WHERE bug_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, bugId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update bug status error: " + e.getMessage());
        }

        return false;
    }
}
