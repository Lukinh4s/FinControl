-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.32-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.17.0.7270
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Copiando dados para a tabela fincontrol.categorias: ~31 rows (aproximadamente)
INSERT INTO `categorias` (`id`, `nome`, `usuario_id`) VALUES
	(2, 'Alimentação', 1),
	(3, 'Mercado', 1),
	(4, 'Restaurantes', 1),
	(5, 'Lanches', 1),
	(6, 'Transporte', 1),
	(7, 'Combustível', 1),
	(8, 'Uber', 1),
	(9, 'Manutenção Veicular', 1),
	(10, 'Moradia', 1),
	(11, 'Aluguel', 1),
	(12, 'Condomínio', 1),
	(13, 'Energia Elétrica', 1),
	(14, 'Água', 1),
	(15, 'Internet', 1),
	(16, 'Telefone', 1),
	(17, 'Saúde', 1),
	(18, 'Farmácia', 1),
	(19, 'Plano de Saúde', 1),
	(20, 'Academia', 1),
	(21, 'Educação', 1),
	(22, 'Cursos', 1),
	(23, 'Livros', 1),
	(24, 'Lazer', 1),
	(25, 'Cinema', 1),
	(26, 'Streaming', 1),
	(27, 'Viagens', 1),
	(28, 'Vestuário', 1),
	(29, 'Presentes', 1),
	(30, 'Investimentos', 1),
	(31, 'Salário', 1),
	(32, 'Categoria Sistema Teste', 1),
	(33, 'Categoria Teste', 1),
	(34, 'Categoria Sistema Teste', 1);

-- Copiando dados para a tabela fincontrol.contas: ~7 rows (aproximadamente)
INSERT INTO `contas` (`id`, `nome`, `saldo`, `tipo_conta`, `usuario_id`) VALUES
	(2, 'Nubank ', 2278.50, 'CONTA_BANCARIA', 2),
	(3, 'Nubank Limite ', 4500.00, 'CARTAO', 2),
	(4, 'Mercado Pago ', 5550.00, 'CONTA_BANCARIA', 1),
	(6, 'Inter', 1500.00, 'CONTA_BANCARIA', 1),
	(7, 'Carteira Física', 200.00, 'CARTEIRA', 1),
	(8, 'Cartão Nubank', 1500.00, 'CARTAO', 1),
	(10, 'Mercado Pago ', 50.00, 'CONTA_BANCARIA', 1),
	(11, 'Conta Sistema Teste', 0.00, 'CONTA_BANCARIA', 1),
	(12, 'Conta Teste', 1400.00, 'CONTA_BANCARIA', 1),
	(13, 'Conta Sistema Teste', 0.00, 'CONTA_BANCARIA', 1);

-- Copiando dados para a tabela fincontrol.transacoes: ~6 rows (aproximadamente)
INSERT INTO `transacoes` (`id`, `descricao`, `valor`, `data_transacao`, `tipo_transacao`, `status_pagamento`, `fonte`, `usuario_id`, `conta_id`, `categoria_id`) VALUES
	(3, 'Freelance Site', 500.00, '2026-05-15', 'RECEITA', NULL, 'Cliente Particular', 1, 2, 30),
	(8, 'Farmácia', 90.00, '2026-05-12', 'DESPESA', 'PAGO', NULL, 1, 2, 17),
	(15, 'Bolsa Familia ', 1500.00, '2026-06-11', 'RECEITA', NULL, NULL, 1, 8, 29),
	(17, 'Mercado Teste', 250.00, '2026-06-11', 'DESPESA', 'PAGO', NULL, 1, 4, 2),
	(19, 'Mercado Teste', 250.00, '2026-06-11', 'DESPESA', 'PAGO', NULL, 1, 4, 2),
	(20, 'Receita Teste', 500.00, '2026-06-11', 'RECEITA', NULL, NULL, 3, 12, 18),
	(21, 'Despesa Teste', 100.00, '2026-06-11', 'DESPESA', 'PAGO', NULL, 3, 12, 18);

-- Copiando dados para a tabela fincontrol.usuarios: ~2 rows (aproximadamente)
INSERT INTO `usuarios` (`id`, `nome`, `email`, `senha`) VALUES
	(1, 'Lucas', 'lucas@gmail.com', '123456'),
	(2, 'Eduardo', 'eduardo@gmail.com', '1234'),
	(3, 'Usuário Teste', 'teste@email.com', '123456');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
