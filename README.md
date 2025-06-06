# 🔌 RedeLuzAPI

API REST desenvolvida com Spring Boot para registrar, consultar e sincronizar ocorrências relacionadas ao fornecimento de energia elétrica, utilizando CEPs e dados simulados de status por região.

---

## 📦 Funcionalidades

- ✅ Registrar novas ocorrências de interrupção elétrica
- ✅ Listar todas as ocorrências registradas
- ✅ Consultar o status de fornecimento de energia por CEP
- ✅ Sincronizar múltiplas ocorrências com dados de status por região

---

## 🧱 Estrutura do Projeto

```
src/
├── controllers/
│   ├── EnergiaController.java
│   └── OcorrenciaController.java
│
├── DTO/
│   ├── EnderecoDTO.java
│   ├── OcorrenciaDTO.java
│   ├── StatusEnergiaDTO.java
│   ├── SincronizacaoRequestDTO.java
│   └── SincronizacaoResponseDTO.java
│
├── model/
│   └── Ocorrencia.java
│
├── repository/
│   └── OcorrenciaRepository.java
│
├── services/
│   └── OcorrenciaService.java
│
└── RedeLuzApiApplication.java
```

---

## 🧪 Exemplos de Requisição

### 🔹 Registrar uma ocorrência

```http
POST /api/ocorrencias
Content-Type: application/json

{
  "localizacao": "Rua A, Bairro B",
  "tempoInterrupcao": 120,
  "impactos": "Sem internet e luz",
  "dispositivoOrigem": "Sensor-001",
  "dataColeta": "2025-06-06T10:30:00",
  "cep": "01001000"
}
```

---

### 🔹 Listar ocorrências

```http
GET /api/ocorrencias
```

---

### 🔹 Consultar status por CEP

```http
GET /api/energia/status/01001000
```

**Resposta esperada:**

```json
{
  "bairro": "Sé",
  "localidade": "São Paulo",
  "statusEnergia": "Funcionando normalmente"
}
```

---

### 🔹 Sincronizar ocorrências em lote

```http
POST /api/energia/sincronizar
Content-Type: application/json

{
  "ocorrencias": [
    {
      "localizacao": "Rua X, Centro",
      "tempoInterrupcao": 90,
      "impactos": "Apagão geral",
      "dispositivoOrigem": "Sensor-009",
      "dataColeta": "2025-06-06T08:00:00",
      "cep": "40010000"
    },
    {
      "localizacao": "Av. Y, Zona Leste",
      "tempoInterrupcao": 45,
      "impactos": "Instabilidade elétrica",
      "dispositivoOrigem": "Sensor-010",
      "dataColeta": "2025-06-06T09:00:00",
      "cep": "30140071"
    }
  ]
}
```

**Resposta esperada:**

```json
{
  "sincronizado": true,
  "ocorrencias": [ ... ],
  "status": {
    "bairro": "Sé",
    "localidade": "São Paulo",
    "statusEnergia": "Funcionando normalmente"
  }
}
```

---

## ⚙️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (ou substituível por PostgreSQL/MySQL)
- Lombok
- Jackson (para JSON)

---

## 🚀 Como executar

1. Clone o repositório:
```bash
git clone https://github.com/seuusuario/repo-rede-luz.git
```

2. Navegue até a pasta:
```bash
cd repo-rede-luz
```

3. Execute o projeto:
```bash
./mvnw spring-boot:run
```

4. Acesse:
```
http://localhost:8080/api/energia/status/{cep}
```

---

## 📬 Contato

Desenvolvido por [Seu Nome] – 💻 Dev Júnior apaixonado por soluções sustentáveis.  
Entre em contato: [seuemail@exemplo.com](mailto:seuemail@exemplo.com)

---
