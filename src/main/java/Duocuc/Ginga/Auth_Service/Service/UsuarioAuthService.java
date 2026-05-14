package Duocuc.Ginga.Auth_Service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import Duocuc.Ginga.Auth_Service.Model.UsuarioAuth;
import Duocuc.Ginga.Auth_Service.Repository.UsuarioAuthRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioAuthService {

    private final UsuarioAuthRepository usuarioRepo;

    public List<UsuarioAuth> listar (){
        return usuarioRepo.findAll();
    }


    public Optional<UsuarioAuth> buscarEmail (String email){
        return usuarioRepo.findByEmail(email).orElseThrow(()-> new RuntimeException("Usuario auth no encontrado por email"));
    }


    public Optional<UsuarioAuth> buscarPorId (Integer id){
        return usuarioRepo.findById(id).orElseThrow(()-> new RuntimeException("Usuario auth no encontrado por id"));
    }

}
