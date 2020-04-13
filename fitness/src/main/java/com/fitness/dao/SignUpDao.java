package com.fitness.dao;

import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitness.infy.service.SignUpService;
import com.fitness.models.EmployeeSignup;
import com.fitness.repository.EmployeeRepository;

@Service
public class SignUpDao implements SignUpService {

	@Autowired
	EmployeeRepository repo;

	private static String secretKey = "boooooooooom!!!!";
	private static String salt = "ssshhhhhhhhhhh!!!!";

	@Override
	public String registerEmployee(EmployeeSignup employee) {

		Optional<EmployeeSignup> fetchedUser = repo.findById(employee.getStaffId());
		EmployeeSignup userFromDB = null;
		if (fetchedUser.isPresent()) {
			userFromDB = fetchedUser.get();
		}
		if (userFromDB != null) {
			return "Employee already registered, please sign in !!!";
		} else {

			employee.setPassword(encrypt(employee.getPassword()));
			repo.save(employee);
		}
		return "Employee registered successfully !!!";
	}

	@Override
	public List<EmployeeSignup> getEmployees() {
		ArrayList<EmployeeSignup> list = (ArrayList<EmployeeSignup>) repo.findAll();
		return list;
	}

	@Override
	public EmployeeSignup getEmployeeById(Integer id) throws Exception {
		Optional<EmployeeSignup> employee = repo.findById(id);
		return employee.get();

	}

	@Override
	public String updateEmployee(EmployeeSignup employee) {
		Optional<EmployeeSignup> fetchedUser = repo.findById(employee.getStaffId());
		if (fetchedUser.isPresent()) {
			employee.setPassword(encrypt(employee.getPassword()));
			repo.save(employee);
			return "Employee details updated successfully !!!";
		} else {
			return "Employee does not exist to update details !!!";
		}

	}

	@Override
	public String deleteEmployeeById(Integer id) {
		repo.deleteById(id);
		Optional<EmployeeSignup> fetchedUser = repo.findById(id);
		if (!fetchedUser.isPresent()) {
			return "Your profile is deleted successfully";
		} else {
			return "Profile is not deleted, please try again";
		}
	}

	@Override
	public boolean authenticateUser(int staffId, String password) {

		Optional<EmployeeSignup> userFromDB = repo.findById(staffId);
		EmployeeSignup fetchedUser = userFromDB.get();

		if (fetchedUser != null && (fetchedUser.getStaffId() == staffId)
				&& password.equals(decrypt(fetchedUser.getPassword()))) {
			return true;

		} else {
			return false;
		}

	}

	public static String decrypt(String strToDecrypt) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static String encrypt(String strToEncrypt) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
}
