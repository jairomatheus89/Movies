import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Home from './pages/Home';
import Login from './pages/login/Login'
import Perfil from './pages/perfil/Perfil'
import FavPerfil from './pages/perfil/FavPerfil'

function App(){
  return(
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/login" element={<Login/>} />
        <Route path="/perfil" element={<Perfil/>} />
        <Route path="/perfil/favorites" element={<FavPerfil/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App
