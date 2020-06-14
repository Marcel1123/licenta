package marcel.pirlog.licenta.userManagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JSONWebToken {
    private String SECRET_KEY;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    private final byte[] apiKeySecretBytes;
    private final Key signingKey;


    public JSONWebToken(){
        String s = "Pentru a creste gradul de confuzie";
        SECRET_KEY = DatatypeConverter.printBase64Binary(s.getBytes());
        apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public String encrypt(String id, String issuer, String subject, long timeMilliseconds){
        long now = System.currentTimeMillis();
        Date currentDate = new Date(now);
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(currentDate)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(this.signatureAlgorithm, this.signingKey);

        if(timeMilliseconds > 0){
            long expirationTime = now + timeMilliseconds;
            Date expire = new Date(expirationTime);
            builder.setExpiration(expire);
        }

        return builder.compact();
    }

    public Claims decrypt(String jwt){
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(this.SECRET_KEY))
                .parseClaimsJws(jwt)
                .getBody();
    }
}
