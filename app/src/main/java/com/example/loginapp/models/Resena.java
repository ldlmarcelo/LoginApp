package com.example.loginapp.models;

public class Resena {
    private int libro;
    private int usuario;
    private String comentario;
    private String fecha_creacion;

    // Constructor
    public Resena(int libro, int usuario, String comentario, String fecha_creacion) {
        this.libro = libro;
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha_creacion = fecha_creacion;
    }

    // Getters
    public int getLibro() {
        return libro;
    }

    public int getUsuario() {
        return usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public String getFechaCreacion() {
        return fecha_creacion;
    }
}
