package com.richard.food.domain.util;

public class ConstantesDomain {

    /** Service **/
    public static final String MSG_EM_COMUM = " %d não pode ser removida, pois está em uso";
    public static final String MSG_COZINHA_EM_USO = "Cozinha " + MSG_EM_COMUM;
    public static final String MSG_ESTADO_EM_USO = "Estado "   + MSG_EM_COMUM;
    public static final String MSG_CIDADE_EM_USO = "Cidade "   + MSG_EM_COMUM;

    /** Exception **/
    public static final String NÃO_EXISTE_UM_CADASTRO = "Não existe um cadastro";
    public static final String NÃO_EXISTE_CADASTRO_RESTAURANTE = NÃO_EXISTE_UM_CADASTRO + " de restaurante com código %d";
    public static final String NÃO_EXISTE_CADASTRO_ESTADO      = NÃO_EXISTE_UM_CADASTRO + " de estado com código %d";
    public static final String NÃO_EXISTE_CADASTRO_CIDADE      = NÃO_EXISTE_UM_CADASTRO + " de cidade com código %d";

}
