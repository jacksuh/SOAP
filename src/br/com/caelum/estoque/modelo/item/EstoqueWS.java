package br.com.caelum.estoque.modelo.item;

import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class EstoqueWS {
    private ItemDao dao = new ItemDao();

    @WebMethod(operationName="TodosOsItens")
    @WebResult(name="itens")
    public ListaItens getItens() {

        System.out.println("Chamando getItens()");
        return new ListaItens(dao.todosItens());
    }


    @WebMethod(operationName="CadastrarItem")
    @WebResult(name="item")
    public Item cadastrarItem(@WebParam(name="tokenUsuario", header=true) TokenUsuario token,
                              @WebParam(name="item") Item item) throws AutorizacaoException {
        System.out.println("Cadastrando " + item + ", " + token);


        boolean valido = new TokenDao().ehValido(token); //o que faremos se o token for invalido?

        if(!valido) {
            throw new AutorizacaoException("Autorizacao falhou");
        }

        this.dao.cadastrar(item);
        return item;
    }

}
