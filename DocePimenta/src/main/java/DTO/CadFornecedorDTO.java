package DTO;

public class CadFornecedorDTO {
    
    private String nomeEmpresa;
    private String nomeFornecedor;
    private String fornecedorProd;
    private int cnpj;
    private double telefone;
    private String nomeEntregador;
    
    
    //getter and setter

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getFornecedorProd() {
        return fornecedorProd;
    }

    public void setFornecedorProd(String fornecedorProd) {
        this.fornecedorProd = fornecedorProd;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public double getTelefone() {
        return telefone;
    }

    public void setTelefone(double telefone) {
        this.telefone = telefone;
    }

    public String getNomeEntregador() {
        return nomeEntregador;
    }

    public void setNomeEntregador(String nomeEntregador) {
        this.nomeEntregador = nomeEntregador;
    }
    
    
}