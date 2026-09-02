Feature: Booking

  @smoke
  Scenario: Criar booking com dados válidos
    Given que possuo um booking válido
    When realizo a criação do booking
    Then o booking deve ser criado com sucesso
    And devo receber o id do booking

  @smoke
  Scenario: Consultar booking por id
    Given que exista um booking criado
    When consulto o booking pelo id
    Then devo receber os dados do booking

  @smoke
  Scenario: Listar bookings
    Given que existam bookings criados
    When solicito a lista de bookings
    Then devo receber uma lista com pelo menos um booking

  @smoke
  Scenario: Atualizar booking (PUT)
    Given que exista um booking criado
    And possuo token de autenticação válido
    When atualizo o booking com novos dados
    Then a atualização deve retornar sucesso
    And os dados atualizados devem ser retornados

  @regression
  Scenario: Atualização parcial (PATCH)
    Given que exista um booking criado
    And possuo token de autenticação válido
    When atualizo parcialmente o booking com um campo
    Then a atualização parcial deve retornar sucesso
    And o campo atualizado deve ser refletido

  @regression
  Scenario: Excluir booking
    Given que exista um booking criado
    And possuo token de autenticação válido
    When realizo a exclusão do booking
    Then a resposta deve indicar sucesso
    And ao consultar o booking o servidor deve retornar 404

  @regression
  Scenario: Atualizar sem autenticação deve falhar
    Given que exista um booking criado
    When tento atualizar o booking sem token
    Then a resposta deve indicar falta de autorização
