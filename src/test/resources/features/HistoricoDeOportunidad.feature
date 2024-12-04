Feature: Histórico de Oportunidad

  Scenario: Generacion del registro histórico al cambiar la etapa de la oportunidad a "Invoiced"
    Given que el usuario ha creado una nueva oportunidad con todos los datos requeridos
    And que el usuario ha añadido un producto a la oportunidad con la información correspondiente
    When el usuario actualiza la etapa de la oportunidad a 'Invoiced'
    Then se debe generar un registro histórico por cada producto relacionado a la oportunidad
    And los campos del registro histórico deben autocompletarse con los datos correspondientes