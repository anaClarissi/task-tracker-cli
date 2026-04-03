# 🧠 Task Tracker CLI

Um gerenciador de tarefas via linha de comando (CLI), desenvolvido em **Java puro**, sem uso de bibliotecas externas.

---

## 📌 Sobre o projeto

Este projeto foi desenvolvido como parte de um desafio da plataforma:

👉 https://roadmap.sh/projects/task-tracker

A proposta do desafio é construir uma aplicação CLI completa para gerenciamento de tarefas, praticando conceitos fundamentais de desenvolvimento backend.

🔗 Meu perfil na plataforma:
👉 https://roadmap.sh/u/anaclarissi

---

## 🚀 Funcionalidades

* ✅ Adicionar tarefas
* 📋 Listar todas as tarefas
* 🔍 Filtrar por status (`todo`, `in-progress`, `done`)
* ✏️ Atualizar tarefas
* ❌ Deletar tarefas
* ✔️ Marcar como concluída
* 🔄 Marcar como em progresso

---

## 🛠️ Tecnologias

* Java 17+
* Manipulação de arquivos (JSON manual)
* CLI (Command Line Interface)

---

## 📦 Como executar o projeto

### ▶️ Executando com `.exe`

```bash id="cmd1"
task-tracker add "Estudar Java"
task-tracker list
task-tracker mark-done 1
```

---

## ⚙️ Tornando o comando global (PATH)

### 1. Copie o executável

Coloque o arquivo `task-tracker.exe` em uma pasta, por exemplo:

```id="cmd2"
C:\task-tracker
```

---

### 2. Adicione ao PATH

1. Pressione `Win + S` → "Variáveis de Ambiente"
2. Clique em **Editar variáveis de ambiente do sistema**
3. Vá em **Variáveis de Ambiente**
4. Edite a variável **Path**
5. Adicione:

```id="cmd3"
C:\task-tracker
```

6. Confirme tudo com OK

---

### 3. Reinicie o terminal

Agora você pode usar:

```bash id="cmd4"
task-tracker list
```

---

## 💻 Comandos disponíveis

```bash id="cmd5"
task-tracker add <description>
task-tracker list
task-tracker list done
task-tracker list todo
task-tracker list in-progress
task-tracker update <id> <description>
task-tracker delete <id>
task-tracker mark-done <id>
task-tracker mark-in-progress <id>
```

---

## 📂 Estrutura do Projeto

```id="cmd6"
📁 src
 ┣ 📁 app          → CLI (entrada do sistema)
 ┣ 📁 service      → regras de negócio
 ┣ 📁 repository   → persistência (JSON)
 ┣ 📁 model        → entidades e enums
```

---

## 📄 Planejamento

👉 [PLANEJAMENTO.md](./PLANEJAMENTO.md)

---

## 🌐 Contato

💼 LinkedIn
👉 https://www.linkedin.com/in/anaclarissi

💻 GitHub
👉 https://github.com/anaClarissi

---

## 🧩 Aprendizados

Durante o desenvolvimento deste projeto, pratiquei:

* Arquitetura em camadas (Controller → Service → Repository)
* Manipulação de arquivos sem bibliotecas externas
* Tratamento de erros em aplicações CLI
* Modelagem de domínio
* Boas práticas em Java

---

## ⭐ Melhorias futuras

* [ ] Implementar parser JSON mais robusto
* [ ] Adicionar testes automatizados
* [ ] Melhorar experiência da CLI
* [ ] Suporte a persistência mais avançada

---

## 📌 Status

🚧 Projeto concluído como desafio, com melhorias contínuas planejadas.

---

Se você gostou do projeto, considere deixar uma ⭐ no repositório!
