package com.example.token_demo.service;

import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.UUID;

@Service
public class AuthorizationService {
    /**
     *   keyId,公钥,私钥 都是用 createKeyPair 方法生成，在本地执行当前类main方法，然后将生成的keyPair 赋值给privateKeyStr和publicKeyStr
     */
    private static String keyId = "0fb0e71291734057be4bd506b5aeb855";
    private static String privateKeyStr = "{\"kty\":\"RSA\",\"kid\":\"0fb0e71291734057be4bd506b5aeb855\",\"alg\":\"ES256\",\"n\":\"sMEUzPmJQD2QR30PZxBSyutWLQ_HZpekDlJCjw-FUNVJa2LOOEz2Gs2pyPg7GHyBhJSFefdGh4LciCxNuE387unTFYt-kLaSEzPKHLyCI8vwMFCSasPIlqbtlCjSm_R4A-C6gcXrPvFOoCk7dOFM9z5yJXGcwhMq7ZBSDVSoYsio9bmfyNQgdueWeibDL4_YrOhW8PLcDKbNJ7a9CPJRyEp-gRbHABDKVva_Tt98O_KPDNVApzhVbk7dhC2OEvKSRAqiPQT1VVYiOTUY0QOky-52hnYrYKHEoyJzymsrcraQxA-mLk80icBY7WwPupQF9v-3pgN8AmKrs-dgHoXN8Q\",\"e\":\"AQAB\",\"d\":\"Prw5Qstq4Kc5N3Z26hDMIgPHcXUBRDOcYgzmXNqYaelaBshqA2eljjvjAFbCut0uJz2D5pdSrDRRS-_Vog3kMXRCnIoHYRu72x7tpKdv1X7EAJIIdeaJopcbChQ3NG1fz5iK-haieZOyYXxhAwoYhETgxNN_XQ7qlKk9xkd_AJg6LVV5qh4Ackm51yGf8PdpOkU38suIzxRu_-Ra6-CVr5oeTzLowPisQrvpc3poQNwSY-5mBE_l0sGTIocGCG7xuMdbvACACXoTZ1cJigL_6Cvvd8HpL2gMsJVI_KM6ZKO681ZKay_f_ielJL7wxpkfVIFlJtqDYVjybGqYByWlQQ\",\"p\":\"7IwwVhBhCCr_noJkAK5xwllD05nU_I7zpfE-mzST6Q-7a1dScWb_ZmDM2O1py_IYaB7V42zZzoXrBMG4ZYwO_dfKhBxOKqwohPQY53d5Y8wGzAqoMz6zgjz4P0x_hPzYKIjV6-l-NDgrkbjIaL8RCnP_0fxpxMV6jebaC-QOPNk\",\"q\":\"v0oeiMD15o8rjWFz8sHWVwVVUJJSOxKJQRJ8_NZdwOgEVwUVQjftpFQa6IiTMowUd7t8AKcMyYzXbZvFe0tqTtZ8ccbn_GrMKVrfzoIn1QtaOgV1MCtWfej88afyxT6QhoHB5BLZFp2W5iB8uEyc1dUz-qiJXrX5oPYJWeNZytk\",\"dp\":\"Cvu7ZtOd3cY5Vj_RquJur8p7RsD2zb9JeuQHtycq0wCDAEnurwtMQpGuEUh8yBZ2oacE4Wl1d4xqTC8-g6CMNacmZRn3Wy3hN8MpwN2gSkz359N62d5IcXypPi8sIJ2o38DyxeBylrQg-cQtsgdlICogr7xboOJWfW5Bo5m0O4k\",\"dq\":\"SVZ7WmbQX_Kn-e5Q69NQ_8_1o4xVpnw2zxHthWoSS7EoaMx0GA0lOQldv6UM-iYmerkQk5d4GZW7yjQchGanfU5SK7TcoDO5zmkewSe5ab6Oeww4n50d7evzfhqrwt93vXnmAjEPtdH5VoVCC86jmn_BC-qtr_gImqN5dlLpzBE\",\"qi\":\"YZAFG3X3qrMz_wPFwPJu2CA36rvd1hxv6-tYVWneOlAQaDsRHlmfN4IXdYLsljtDi_Qp-I-Z0LPnZFMgiTUPQ8yzWUPJ31YpZePDxMtc5esI0M19ktofqVrH-NxPKvGLfPBsD5p_u5OtKkztr_Vq6yXhj3tSO8TPSXheUILxmdE\"}";
    private static String publicKeyStr = "{\"kty\":\"RSA\",\"kid\":\"0fb0e71291734057be4bd506b5aeb855\",\"alg\":\"ES256\",\"n\":\"sMEUzPmJQD2QR30PZxBSyutWLQ_HZpekDlJCjw-FUNVJa2LOOEz2Gs2pyPg7GHyBhJSFefdGh4LciCxNuE387unTFYt-kLaSEzPKHLyCI8vwMFCSasPIlqbtlCjSm_R4A-C6gcXrPvFOoCk7dOFM9z5yJXGcwhMq7ZBSDVSoYsio9bmfyNQgdueWeibDL4_YrOhW8PLcDKbNJ7a9CPJRyEp-gRbHABDKVva_Tt98O_KPDNVApzhVbk7dhC2OEvKSRAqiPQT1VVYiOTUY0QOky-52hnYrYKHEoyJzymsrcraQxA-mLk80icBY7WwPupQF9v-3pgN8AmKrs-dgHoXN8Q\",\"e\":\"AQAB\"}";

    public static long accessTokenExpirationTime = 60;
    public static long refreshTokenExpirationTime = 60*3;

    public String createAccessIdToken(String userId) {
        return createIdToken(userId,accessTokenExpirationTime);
    }

    public String createRefreshIdToken(String userId) {
        return createIdToken(userId,refreshTokenExpirationTime);
    }

    public String createIdToken(String account,long expirationTime) {
        try {
            //Claims
            JwtClaims claims = new JwtClaims();
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();
            //expire time
            NumericDate date = NumericDate.now();
            date.addSeconds(expirationTime);
            claims.setExpirationTime(date);
            claims.setNotBeforeMinutesInThePast(1);
            claims.setSubject("YOUR_SUBJECT");
            claims.setAudience("YOUR_AUDIENCE");
            //添加自定义参数,必须是字符串类型
            claims.setClaim("account", account);

            //jws
            JsonWebSignature jws = new JsonWebSignature();
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
            jws.setKeyIdHeaderValue(keyId);
            jws.setPayload(claims.toJson());
            PrivateKey privateKey = new RsaJsonWebKey(JsonUtil.parseJson(privateKeyStr)).getPrivateKey();
            jws.setKey(privateKey);

            //get token
            String idToken = jws.getCompactSerialization();
            return idToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * jws校验token
     *
     * @param token
     * @return 返回 用户账号
     * @throws JoseException
     */
    public String verifyToken(String token) {
        try {
            JwtConsumer consumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime()
                    .setMaxFutureValidityInMinutes(5256000)
                    //allow some leeway in validating time based claims to account for clock skew
                    //过期后30秒内还能解析成功
                    .setAllowedClockSkewInSeconds(30)
                    .setRequireSubject()
                    //.setExpectedIssuer("")
                    .setExpectedAudience("YOUR_AUDIENCE")
                    /*
                    RsaJsonWebKey jwk = null;
                    try {
                        jwk = RsaJwkGenerator.generateJwk(2048);
                        } catch (JoseException e) {
                            e.printStackTrace();
                        }
                        jwk.setKeyId(keyId); */
                    //.setVerificationKey(jwk.getPublicKey())
                    .setVerificationKey(new RsaJsonWebKey(JsonUtil.parseJson(publicKeyStr)).getPublicKey())
                    .build();

            JwtClaims claims = consumer.processToClaims(token);
            if (claims != null) {
                String account = (String) claims.getClaimValue("account");
                System.out.println("认证通过！token payload携带的自定义内容:用户账号account=" + account);
                return account;
            }
        }  catch (JoseException e) {
            e.printStackTrace();
        }  catch (InvalidJwtException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建jwk keyId , 公钥 ，秘钥
     */
    public static void createKeyPair(){
        String keyId = UUID.randomUUID().toString().replaceAll("-", "");
        RsaJsonWebKey jwk = null;
        try {
            jwk = RsaJwkGenerator.generateJwk(2048);
        } catch (JoseException e) {
            e.printStackTrace();
        }
        jwk.setKeyId(keyId);
        jwk.setAlgorithm(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256);
        String publicKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.PUBLIC_ONLY);
        String privateKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.INCLUDE_PRIVATE);

        System.out.println("keyId="+keyId);
        System.out.println();
        System.out.println("公钥 publicKeyStr="+publicKey);
        System.out.println();
        System.out.println("私钥 privateKeyStr="+privateKey);
    }
    
    public static void main(String[] args){
        //create key pair
        createKeyPair();
    }
}
