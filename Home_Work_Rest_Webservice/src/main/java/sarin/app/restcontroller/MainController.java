package sarin.app.restcontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sarin.app.entitie.UserDTO;
import sarin.app.service.UserServices;




@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	UserServices studentImplement; 
	
	/*function for load home page*/
	@RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
	public ModelAndView list() throws SQLException{			
		ModelAndView model=new ModelAndView("index");
		System.out.println(123);
		return model;
	}
	
	/*function for respone data to view*/
	@RequestMapping(value={"/list.act"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getAllStudent() throws SQLException{
		
		ArrayList<UserDTO> user = studentImplement.list();
		System.out.println(studentImplement.list().get(0).getUser_name());
		Map<String, Object> map = new HashMap<String,Object>();
		if(user.isEmpty()){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "USER NOT FOUND...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "USER HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA", studentImplement.list());
		return new ResponseEntity<Map<String,Object>>
									(map,HttpStatus.OK);
		
		
	}

	/*function  request add*/
	@RequestMapping(value={"/add"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addStudent(@RequestBody UserDTO user) throws SQLException{
		
		
		Map<String, Object> map = new HashMap<String,Object>();
		if(studentImplement.insert(user)){
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("MESSAGE", "INSERT SUCCESS...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.OK);
		}
		else{
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "INSERT NOT SUCCESS...");			
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	/*function for request update*/
	@RequestMapping(value={"/update"}, method = RequestMethod.PUT)
	
public ResponseEntity<Map<String,Object>> UpdateStudent(@RequestBody UserDTO user) throws SQLException{				
		Map<String, Object> map = new HashMap<String,Object>();
		if(studentImplement.update(user)){
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("MESSAGE", "Update  SUCCESS...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.OK);
		}
		else{
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "Update NOT SUCCESS...");			
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}
		
		
	}
	/*function request for delete user*/
	@RequestMapping(value={"/delete/{id}"}, method = RequestMethod.DELETE)	
	public ResponseEntity<Map<String,Object>> DeleteStudent(@PathVariable("id") int id) throws SQLException{
		 
		
			
			
			Map<String, Object> map = new HashMap<String,Object>();
			if(studentImplement.delete(id)){
				map.put("STATUS", HttpStatus.CREATED.value());
				map.put("MESSAGE", "Delete  SUCCESS...");
				return new ResponseEntity<Map<String,Object>>
											(map,HttpStatus.OK);
			}
			else{
				map.put("STATUS", HttpStatus.NOT_FOUND.value());
				map.put("MESSAGE", "Delete NOT SUCCESS...");			
				return new ResponseEntity<Map<String,Object>>
											(map,HttpStatus.NOT_FOUND);
			}
			
			
		}
	
	/*function request data for update*/
	@RequestMapping(value={"/update/{id}"}, method = RequestMethod.GET)
		
		public ResponseEntity<Map<String,Object>> updateForm(@PathVariable("id") int id) throws SQLException{
		System.out.println(studentImplement.update(id));
		ArrayList<UserDTO> students = studentImplement.update(id);
		Map<String,Object> map= new HashMap<String, Object>();
		if(students.isEmpty()){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "STUDENT NOT FOUND...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}		
		
				map.put("STATUS", HttpStatus.OK.value());
				map.put("MESSAGE", "STUDENT HAS BEEN FOUNDS");
				map.put("RESPONSE_DATA",studentImplement.update(id));							
				return new ResponseEntity<Map<String,Object>>
				(map,HttpStatus.OK);
			
		}
			
		
	
	
	
	/*function request data for search*/
	@RequestMapping(value={"/search/{s}"}, method = RequestMethod.GET)	
		public ResponseEntity<Map<String,Object>> searchAction(@PathVariable("s") String key) throws SQLException{
		System.out.println(key);
		ArrayList<UserDTO> students = studentImplement.search(key);
		Map<String, Object> map = new HashMap<String,Object>();
		if(students.isEmpty()){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "User NOT FOUND...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}
		System.out.println(studentImplement.search(key).size());
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "User HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA", studentImplement.search(key));
		return new ResponseEntity<Map<String,Object>>
									(map,HttpStatus.OK);
		
		
						
	}
		
}
