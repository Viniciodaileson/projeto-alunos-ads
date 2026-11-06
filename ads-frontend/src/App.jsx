import { useState, useEffect } from 'react';

function App() {
  const [alunos, setAlunos] = useState([]);

  // Busca os dados lá no seu servidor Java (Porta 8080) toda vez que a página abre
  useEffect(() => {
    fetch('http://localhost:8080/api/alunos')
      .then(response => response.json())
      .then(data => setAlunos(data))
      .catch(error => console.error("Erro ao conectar com o Java:", error));
  }, []);

  return (
    <div style={{ padding: '40px', fontFamily: 'sans-serif', backgroundColor: '#fafafa', minHeight: '100vh' }}>
      <h1 style={{ color: '#333' }}>🎓 Painel de Alunos - ADS</h1>
      <p style={{ color: '#666' }}>Dados vindos diretamente do Back-End em Java</p>
      
      {/* Tabela Visual */}
      <table border="1" cellPadding="12" style={{ width: '100%', borderCollapse: 'collapse', marginTop: '20px', backgroundColor: '#fff', boxShadow: '0 2px 5px rgba(0,0,0,0.05)' }}>
        <thead>
          <tr style={{ backgroundColor: '#4A90E2', color: '#fff', textAlign: 'left' }}>
            <th>ID</th>
            <th>Nome</th>
            <th>E-mail</th>
            <th>Curso</th>
          </tr>
        </thead>
        <tbody>
          {alunos.length === 0 ? (
            <tr>
              <td colSpan="4" style={{ textAlign: 'center', color: '#999' }}>Nenhum aluno encontrado ou o servidor Java está desligado.</td>
            </tr>
          ) : (
            alunos.map(aluno => (
              <tr key={aluno.id} style={{ borderBottom: '1px solid #eee' }}>
                <td style={{ fontWeight: 'bold' }}>{aluno.id}</td>
                <td>{aluno.nome}</td>
                <td>{aluno.email}</td>
                <td style={{ color: '#555' }}>{aluno.curso}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default App;
