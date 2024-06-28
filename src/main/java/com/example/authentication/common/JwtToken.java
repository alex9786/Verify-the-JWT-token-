package com.example.authentication.common;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.example.authentication.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {
	
	private static long expiryDuration = 60*60;
	private static String  secret = "This is secret ";
	
	public String generateJwt(User user) {

		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;
		Date issudedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		Claims claims = Jwts.claims().setIssuer(user.getId().toString()).setIssuedAt(issudedAt);
		claims.setIssuedAt(issudedAt).setExpiration(expiryAt);
		claims.put("name", user.getName());
		claims.put("emailid", user.getEmailId());
		claims.put("contact", user.getContact());
		claims.put("gender", user.getGender());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Claims verify(String authorization) throws Exception {
		try {
			Claims claim = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
			return claim;

		} catch (Exception e) {
			throw new Exception();
		}
	}
	

}
