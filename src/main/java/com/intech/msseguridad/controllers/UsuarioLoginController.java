package com.intech.msseguridad.controllers;
import com.intech.msseguridad.cross.JwtTokenCross;
import com.intech.msseguridad.dtos.AuthRequest;
import com.intech.msseguridad.dtos.AuthResponse;
import com.intech.msseguridad.exceptions.InternalServerErrorException;
import com.intech.msseguridad.message.TransactionMessagePublish;
import com.intech.msseguridad.models.UsuarioLoginModel;
import com.intech.msseguridad.services.IUsuarioLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class UsuarioLoginController {
    @Autowired
    IUsuarioLoginService usuarioLoginService;

    @Autowired
    private TransactionMessagePublish transactionMessagePublish;
    @Autowired
    private JwtTokenCross jwtTokenCross;
    Logger logger = LoggerFactory.getLogger(UsuarioLoginController.class);

    @GetMapping
    public List<UsuarioLoginModel> get() {

        return usuarioLoginService.getAcces();
    }
    @PostMapping()
    public ResponseEntity<?> post(@RequestBody AuthRequest request) throws InternalServerErrorException {
        try {
            logger.info("Post: UserName {} - Password {}", request.getUserName(), request.getPassword());


            if (!usuarioLoginService.validatedCredentials(request.getUserName(), request.getPassword())) {
                return new ResponseEntity<String>("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
            }

            AuthResponse response = new AuthResponse(jwtTokenCross.generateToken(request), request.getUserName(), "1d");
            transactionMessagePublish.sendLoginEvent(request);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Ocurrió un error interno en el servidor.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error interno en el servidor. Intenta de nuevo en unos segundos.");
        }
    }
}