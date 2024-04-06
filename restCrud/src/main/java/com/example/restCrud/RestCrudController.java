package com.example.restCrud;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class RestCrudController {

    private List<Usuario> usuarios = new ArrayList<>();

    //função: Read
    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarios;
    }

    //função: Create
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        usuario.setId(generateNextId()); //Atribui um ID único
        usuarios.add(usuario);
        return usuario;
    }


    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioExistente = usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        usuarioExistente.setNome(usuario.getNome());
        //atualize outros atributos se necssário

        return usuarioExistente;
    }

}
