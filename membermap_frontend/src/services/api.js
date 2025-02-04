import axios from 'axios';

const api = axios.create({
  baseURL: '',  // 빈 문자열 또는 생략 가능
});

// 예: 상대 경로로 요청
export const callHuggingFaceAPI = (data) => {
  return api.post('/api/huggingface/call', data);
};

export default api;
