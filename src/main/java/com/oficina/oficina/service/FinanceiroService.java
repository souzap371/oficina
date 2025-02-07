// package com.oficina.oficina.service;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import com.oficina.oficina.model.Financeiro;
// import com.oficina.oficina.repository.FinanceiroRepository;
// import java.util.List;

// @Service
// public class FinanceiroService {

//     @Autowired
//     private FinanceiroRepository financeiroRepository;

//     public List<Financeiro> listarTodos() {
//         return financeiroRepository.findAll();
//     }

//     public void salvar(Financeiro financeiro) {
//         financeiroRepository.save(financeiro);
//     }

//     public void excluir(Long id) {
//         financeiroRepository.deleteById(id);
//     }
// }


package com.oficina.oficina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oficina.oficina.model.Financeiro;
import com.oficina.oficina.repository.FinanceiroRepository;

import java.util.List;

@Service
public class FinanceiroService {

    @Autowired
    private FinanceiroRepository financeiroRepository;

    public List<Financeiro> listarTodos() {
        return financeiroRepository.findAll();
    }

    public void salvar(Financeiro financeiro) {
        financeiroRepository.save(financeiro);
    }

    public void excluir(Long id) {
        financeiroRepository.deleteById(id);
    }
    public boolean existePorId(Long id) {
        return financeiroRepository.existsById(id);
    }

    public double calcularTotalReceita() {
        return financeiroRepository.findAll().stream()
                .filter(financeiro -> "RECEITA".equals(financeiro.getTipoMovimento()))
                .mapToDouble(Financeiro::getValor)
                .sum();
    }

    public double calcularTotalDespesa() {
        return financeiroRepository.findAll().stream()
                .filter(financeiro -> "DESPESA".equals(financeiro.getTipoMovimento()))
                .mapToDouble(Financeiro::getValor)
                .sum();
    }
    public double calcularValorLiquido() {
        return calcularTotalReceita() - calcularTotalDespesa();
    }
}
