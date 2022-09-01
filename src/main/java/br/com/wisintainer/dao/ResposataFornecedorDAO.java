package br.com.wisintainer.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.wisintainer.helper.HibernateUtil;
import br.com.wisintainer.helper.SQLBuilder;
import br.com.wisintainer.helper.SQLBuilder.Mode;
import br.com.wisintainer.model.Fornecedor;
import br.com.wisintainer.model.RespostaFornecedor;
import br.com.wisintainer.model.RespostaOrcamento;

public class ResposataFornecedorDAO extends GenericDAO {
	public ResposataFornecedorDAO() {
		super(HibernateUtil.getSessionMysqlFactory());
	}

	public ResposataFornecedorDAO(Session session) {
		super(HibernateUtil.getSessionMysqlFactory(), session);
	}

	public RespostaFornecedor buscarRespostaOrcamentoPorId(Integer id) throws Exception {
		SQLBuilder sb = new SQLBuilder(Mode.SQL);
		sb.append(" SELECT * FROM respostafornecedor WHERE id = :id ");
		sb.setParameter("id", id);

		return getSingle(sb, RespostaFornecedor.class);
	}

	public List<RespostaFornecedor> buscarRespostasFornecedoresPorIdOrcamento(Integer id) throws Exception {
		SQLBuilder sb = new SQLBuilder(Mode.SQL);
		sb.append(" SELECT * FROM respostafornecedor WHERE id_orcamento = :id AND fornecedorrespondeu = true ");
		sb.setParameter("id", id);

		return getArrayList(sb, RespostaFornecedor.class);
	}

	public void atualizarResposta(Integer idResposta) throws Exception {
		SQLBuilder sb = new SQLBuilder(Mode.SQL);
		sb.append(" UPDATE respostafornecedor SET fornecedorrespondeu = true WHERE id = :id ");
		sb.setParameter("id", idResposta);

		execute(sb);
	}

	public List<RespostaOrcamento> buscarRespostasOrcamentoPorOrcamentoId(Integer formecedorId, Integer orcamentoId) throws Exception {
		SQLBuilder sb = new SQLBuilder(Mode.SQL);
		sb.append("SELECT item.produtoServico, item.quantidade, ro.valor, ro.tememestoque from respostaorcamento ro");
		sb.append("INNER JOIN itemorcamento item ON item.id = ro.id_item");
		sb.append("INNER JOIN respostafornecedor rf ON rf.id = ro.id_resposta");
		sb.append("WHERE rf.id_fornecedor = 7 AND ro.id_orcamento = 44");
		sb.setParameter("formecedorId", formecedorId);
		sb.setParameter("orcamentoId", orcamentoId);

		return getArrayList(sb, RespostaOrcamento.class);
	}
}
