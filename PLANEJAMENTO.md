# 📌 Planejamento de Projeto: Task Tracker CLI (Java)

## 🧠 1. Visão Geral
Este projeto consiste no desenvolvimento de uma aplicação de linha de comando (CLI) em **Java puro** para o gerenciamento de tarefas (*to-do list*). O sistema permitirá criar, atualizar, excluir e listar tarefas, garantindo a persistência dos dados em um arquivo local no formato JSON.

## 🏗️ 2. Arquitetura do Projeto
A aplicação segue o padrão de arquitetura em camadas para garantir a separação de responsabilidades:

$$\text{CLI (Interface)} \rightarrow \text{Service (Regras de Negócio)} \rightarrow \text{Repository (Persistência)}$$

### 📁 Estrutura de Diretórios
```text
task-cli/
│
├── src/
│   ├── app/
│   │   └── Program.java          # Ponto de entrada (Main) e interpretador de comandos
│   │
│   ├── model/
│   │   ├── entities/
│   │   │   └── Task.java         # Entidade que representa a tarefa
│   │   │
│   │   └── enums/
│   │       └── TaskStatus.java   # Estados possíveis da tarefa
│   │
│   ├── service/
│   │   └── TaskService.java      # Implementação das regras de negócio
│   │
│   ├── repository/
│   │   └── TaskRepository.java   # Gerenciamento de leitura/escrita do arquivo
│   │
│   └── util/
│       └── JsonUtil.java         # Funções auxiliares para manipulação de strings JSON
│
└── tasks.json                    # Banco de dados em arquivo plano
```

---

## 🧱 3. Modelagem de Dados

### 📍 `Task.java`
Classe responsável por representar a abstração de uma tarefa no sistema.
* **`id`** `(Integer)`: Identificador único e autoincrementável.
* **`description`** `(String)`: Descrição da ação a ser realizada.
* **`status`** `(TaskStatus)`: Estado atual da tarefa.
* **`createdAt`** `(LocalDateTime)`: Data e hora de criação.
* **`updatedAt`** `(LocalDateTime)`: Data e hora da última modificação.

### 📍 `TaskStatus.java`
Enumeração que restringe os estados que uma tarefa pode assumir:
* `TODO` (A fazer)
* `IN_PROGRESS` (Em andamento)
* `DONE` (Concluída)

---

## 💾 4. Camada de Persistência (JSON)
O arquivo `tasks.json` funcionará como nosso banco de dados não-relacional.

> ⚠️ **Regra de Ouro:** A leitura e escrita das strings JSON devem ser feitas de forma manual, sem o uso de bibliotecas externas (como Jackson ou Gson), exercitando a manipulação de Strings e arquivos em Java.

### Exemplo de Estrutura do Arquivo
```json
[
  {
    "id": 1,
    "description": "Estudar a API de Streams do Java",
    "status": "IN_PROGRESS",
    "createdAt": "2026-04-01T10:00:00",
    "updatedAt": "2026-04-01T15:30:00"
  }
]
```

---

## ⚙️ 5. Definição das Interfaces e Métodos

### 📦 `TaskRepository.java`
| Método | Retorno | Descrição |
| :--- | :--- | :--- |
| `findAll()` | `List<Task>` | Lê o arquivo JSON e retorna a lista de tarefas mapeadas. |
| `saveAll(List<Task> tasks)` | `void` | Sobrescreve o arquivo JSON com a lista atualizada de tarefas. |

### 🛠️ `TaskService.java`
Centraliza as validações e chamadas ao repositório.
* `add(String description)`
* `update(int id, String description)`
* `delete(int id)`
* `markInProgress(int id)`
* `markDone(int id)`
* `listAll()`
* `listByStatus(TaskStatus status)`

---

## 🖥️ 6. Interface de Linha de Comando (CLI)
A interação do usuário com o sistema será feita exclusivamente passando argumentos na execução do terminal.

### Mapeamento de Comandos
| Ação | Comando no Terminal |
| :--- | :--- |
| **Adicionar** | `java Program add "Nova tarefa"` |
| **Atualizar** | `java Program update 1 "Tarefa modificada"` |
| **Excluir** | `java Program delete 1` |
| **Listar Todas** | `java Program list` |
| **Listar por Status**| `java Program list done` \| `list todo` \| `list in-progress` |
| **Mudar Status** | `java Program mark-in-progress 1` \| `mark-done 1` |

---

## 🧩 7. Roadmap de Implementação

- [ ] **Fase 1: Fundação**
    - Criar `Task.java` e `TaskStatus.java`.
    - Implementar esqueleto do `TaskRepository` para criar o arquivo caso não exista.
- [ ] **Fase 2: Fluxo de Escrita**
    - Implementar `JsonUtil` para converter objeto Java para String formatada em JSON.
    - Implementar a função de adicionar tarefas no Service e Repository.
- [ ] **Fase 3: Fluxo de Leitura**
    - Implementar o parser manual de JSON para Objeto Java no `JsonUtil`.
    - Implementar métodos de listagem.
- [ ] **Fase 4: Edição e Deleção**
    - Implementar buscas por ID e deleção física ou lógica.
    - Adicionar travas para IDs inexistentes.
- [ ] **Fase 5: Polimento e CLI**
    - Amarrar os argumentos do `args[]` da classe `Program` com os métodos do Service.
    - Criar tratamento visual para erros de input do usuário.

---

## 💡 Boas Práticas e Pontos de Atenção
* **IDs Únicos:** O sistema deve calcular o próximo ID baseado no maior ID existente no arquivo para evitar colisões.
* **Tratamento de Datas:** Use a API `java.time.LocalDateTime` para garantir precisão e ISO-8601 no JSON.
* **Commits Semânticos:** Mantenha o histórico do Git limpo utilizando o padrão Conventional Commits (ex: `feat:`, `fix:`, `refactor:`).

---

## 🚀 Próximos Passos (Evolução)
* [ ] Implementar suporte a cores no terminal (ANSI escape codes).
* [ ] Criar um comando interativo estilo shell (onde o programa não fecha após um comando).
* [ ] Migrar a persistência manual para a biblioteca **Jackson** para fins de comparação de performance e legibilidade de código.

---