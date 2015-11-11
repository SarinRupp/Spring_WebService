package sarin.app.service;

import java.sql.SQLException;
import java.util.ArrayList;

import sarin.app.entitie.UserDTO;

public interface UserServices {

		ArrayList<UserDTO> list() throws SQLException;
		boolean delete(int id) throws SQLException;
		boolean insert(UserDTO user) throws SQLException;
		boolean update(UserDTO user) throws SQLException;
		ArrayList<UserDTO> search(String keyword) throws SQLException;
		ArrayList<UserDTO> update(int id) throws SQLException;
}
