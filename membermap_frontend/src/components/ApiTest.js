// src/components/ApiTest.js
import React, { useState } from 'react';
import api from '../services/api';

const ApiTest = () => {
  const [inputText, setInputText] = useState('');
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const handleCallApi = async () => {
    try {
      const response = await api.post('/api/huggingface/call', { input: inputText });
      setResult(response.data);
      setError(null);
    } catch (err) {
      console.error(err);
      setError('API 호출 실패');
      setResult(null);
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Hugging Face API 테스트</h2>
      <input
        type="text"
        placeholder="텍스트 입력"
        value={inputText}
        onChange={(e) => setInputText(e.target.value)}
        style={{ width: '300px', padding: '8px' }}
      />
      <button onClick={handleCallApi} style={{ marginLeft: '10px', padding: '8px 16px' }}>
        API 호출
      </button>
      {result && (
        <div style={{ marginTop: '20px' }}>
          <h3>응답 결과:</h3>
          <pre>{JSON.stringify(result, null, 2)}</pre>
        </div>
      )}
      {error && (
        <div style={{ marginTop: '20px', color: 'red' }}>
          <h3>오류:</h3>
          <p>{error}</p>
        </div>
      )}
    </div>
  );
};

export default ApiTest;
