package com.kea.planit.repositories;

import com.kea.planit.models.Subproject;
import com.kea.planit.utilities.DBconnector;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubprojectRepository {

    public ArrayList<Subproject> getSubprojectsInThisProject(int subprojectOwner) {
        ArrayList<Subproject> subprojectList = new ArrayList<>();

        try {
            //todo: use setInt instead of concatenation
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("SELECT * FROM subprojects WHERE subproject_owner = " + subprojectOwner);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Subproject subproject = new Subproject(
                        rs.getInt("id"), //id
                        rs.getString("name"), //name
                        rs.getDate("deadline"), //deadline
                        rs.getInt("subproject_owner") //
                );
                subprojectList.add(subproject);
            }
        }
        catch(SQLException e){
            System.out.println("Something went wrong when fetching subprojects from database");
            System.out.println(e.getMessage());
        }
        //todo: why does this fire three times?
        System.out.println("Returned subproject list"); //debug
        return subprojectList;
    }

    //NOT FINISHED
    public Subproject fetchSubprojectById(int id) {
        try {
            //String selectStatement = "SELECT * FROM tasks WHERE id = ?";

            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("SELECT * FROM subprojects WHERE id = ?");

            preparedStatement.setString(1, String.valueOf(id));

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Subproject fetchedSubproject = new Subproject(
                        rs.getInt("id"), //id
                        rs.getString("name"), //name
                        rs.getDate("deadline"), //deadline
                        rs.getInt("subproject_owner") //
                );
                System.out.println("Subproject (id: " + fetchedSubproject.getId() + ") fetched");
                return fetchedSubproject;
            }
        }
        catch(SQLException e){
            System.out.println("Something went wrong when fetching subproject from database");
            System.out.println(e.getMessage());
        }
        System.out.println("Subproject not fetched, returned null"); //debug
        return null;
    }

    public void addToSubprojectList(Subproject newSubproject) {

        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("INSERT INTO subprojects VALUES (default,?,?,?)"); //default because it auto increments
            preparedStatement.setString(1, newSubproject.getName());
            preparedStatement.setDate(2, Date.valueOf("2022-12-12")); //test
            preparedStatement.setInt(3, newSubproject.getSubprojectOwner());
            preparedStatement.execute();
            System.out.println("Added subproject to database");
        } catch(SQLException exception) {
            System.out.println("Something went wrong when adding subproject to database");
            exception.printStackTrace();
        }
    }
    public void editSubproject(Subproject editedSubproject) {

        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement(
                    "UPDATE subprojects SET name = ?, deadline = ? WHERE id = ?");
            preparedStatement.setString(1, editedSubproject.getName());
            preparedStatement.setDate(2, Date.valueOf("2022-12-12")); //test because it's not working
            preparedStatement.setInt(3, editedSubproject.getId());
            preparedStatement.executeUpdate();
            System.out.println("Subproject edited (id: " + editedSubproject.getId() + ")"); //debug
        } catch (SQLException e) {
            System.out.println("Something went wrong when editing subproject");
            e.printStackTrace();
        }
    }

    public void deleteSubproject(int subprojectId) {
        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("DELETE FROM subprojects WHERE id = ?");
            preparedStatement.setInt(1, subprojectId);
            preparedStatement.execute();
            System.out.println("Deleted subproject");
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete subprojects from database");
            e.printStackTrace();
        }
    }
}
