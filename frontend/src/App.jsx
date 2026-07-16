import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'

function MoviesContents(){
  const [movies, setMovies] = useState([]);
  const [moviesPage, setMoviesPage] = useState(0);

  function nextPage(){
    setMoviesPage(moviesPage + 1);
  }
  function lastPage(){
    if(moviesPage > 0) setMoviesPage(moviesPage - 1);
  }

  useEffect(() => {
    async function fetchMovies(){
      const res = await fetch(`http://localhost:8080/?page=${moviesPage}`);
      const response = await res.json();
      if(response != null) setMovies(response.content);
    }

    fetchMovies();
    
  }, [moviesPage])

  if(movies != null){console.log(movies)}

  return(
    <div className='moviesContents'>
      <div className='moviesCatalog'>
        {
          movies != null
          ? movies.map(movie => (
            <div key={movie.id} className='movieCards'>
              <img className='moviePoster' src={`https://image.tmdb.org/t/p/w500${movie.posterPath}`} alt="movie.title" />
              <span className='movieTitle'>{movie.title}</span>
            </div>
          ))
          : <div>TEM NADA DE FILMES AQUI...</div>
        }
      </div>
      
      <div className='selectPages'>
        <button className='pageButtons' onClick={lastPage}>&lt;</button>
        <span className='pageSpan'>Page: {moviesPage}</span>
        <button className='pageButtons' onClick={nextPage}>&gt;</button>
      </div>
    </div>
  )

}

function App() {

  

  return (
    <div id="body">
      <header>
        <img className='logozin' src={reactLogo} alt="" />
        <h1 className='headerDiv'>Jairo Movies</h1>
        <div className='headerOption'>
          <p className='headerOptionObjs'>SignIn</p><span>|</span><p className='headerOptionObjs'>SignUp</p>
        </div>
      </header>

      <MoviesContents/>

      <footer>
        <p className='footerDiv'>&copy;2026 Jairo Matheus. All rights reserved</p>
      </footer>
    </div>
  )
}

export default App
