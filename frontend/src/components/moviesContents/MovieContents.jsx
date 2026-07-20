import { useEffect, useState } from 'react'

import styles from './MovieContents.module.css'

function MoviesContents(){
  const [movies, setMovies] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [moviesPage, setMoviesPage] = useState(0);

  function nextPage(){
    setMoviesPage(moviesPage + 1);
  }
  function lastPage(){
    if(moviesPage > 0) setMoviesPage(moviesPage - 1);
  }

  useEffect(() => {
    async function fetchMovies(){
      const res = await fetch(`http://localhost:3000/?page=${moviesPage}`);
      const response = await res.json();
      if(response != null){
        setTotalPages(response.totalPages);
        setMovies(response.content);
      }
    }

    fetchMovies();
  }, [moviesPage])

  if(movies != null){console.log(movies)}

  return(
    <div className={styles.moviesContents}>
      <div className={styles.moviesCatalog}>
        {
          movies != null
          ? movies.map(movie => (
            <div key={movie.id} className={styles.movieCards}>
              <img className={styles.moviePoster} src={`https://image.tmdb.org/t/p/w500${movie.posterPath}`} alt="movie.title" />
              <span className={styles.movieTitle}>{movie.title}</span>
            </div>
          ))
          : <div>TEM NADA DE FILMES AQUI...</div>
        }
      </div>
      
      <div className={styles.selectPages}>
        <button className={styles.pageButtons} onClick={lastPage}>&lt;</button>
        <span className={styles.pageSpan}>{moviesPage + 1}/{totalPages}</span>
        <button className={styles.pageButtons} onClick={nextPage}>&gt;</button>
      </div>
    </div>
  )

}

export default MoviesContents;