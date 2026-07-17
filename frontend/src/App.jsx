import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Home from './pages/Home';
import Login from './pages/login/Login'
import Perfil from './pages/perfil/Perfil'

function App(){
  return(
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/login" element={<Login/>} />
        <Route path="/perfil" element={<Perfil/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App
