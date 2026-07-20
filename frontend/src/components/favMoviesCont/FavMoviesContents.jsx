import { useEffect, useState } from 'react'

import styles from './FavMoviesContents.module.css'


function FavMoviesContents(){
  const [movies, setMovies] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [moviesPage, setMoviesPage] = useState(0);

  function nextPage(){
    if(moviesPage < totalPages - 1){
      setMoviesPage(moviesPage + 1);
    }
  }
  function lastPage(){
    if(moviesPage > 0){
      setMoviesPage(moviesPage - 1);
    }
  }

  useEffect(() => {
    async function fetchMovies(){
      const res = await fetch(
        `http://192.168.1.102:3000/user/profile/myfavorites?page=${moviesPage}`,{
          method: 'GET',
          headers: {
              'Authorization': `Bearer ${localStorage.getItem("token")}`
          }
        }
      );
      const response = await res.json();
      if(response != null){
        setTotalPages(response.totalPages);
        setMovies(response.content);
      }
    }

    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });

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
              <div className={styles.moviePoster}>
                <img src={`https://image.tmdb.org/t/p/w500${movie.posterPath}`} alt={movie.title}/>
                {/* <span className={styles.movieTitle}>{movie.title}</span> */}
              </div>
              <div className={styles.movieInfo}>
                <h3>Titulo: {movie.title}</h3>
                <p>• Data de lançamento: {movie.releaseDate}</p>
                <p>• Média de votos: {movie.voteAverage}</p>
                <p>• Contagem de votos: {movie.voteCount}</p>
                <p>• Sinopse: {movie.overview}</p>
              </div>
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

export default FavMoviesContents;