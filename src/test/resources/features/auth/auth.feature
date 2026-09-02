Feature: Autenticação de usuário

  @smoke
  Scenario: Realizar autenticação com credenciais válidas
    Given que possuo credenciais válidas
    When realizo a autenticação
    Then a autenticação deve ser realizada com sucesso
    And devo receber um token de autenticação