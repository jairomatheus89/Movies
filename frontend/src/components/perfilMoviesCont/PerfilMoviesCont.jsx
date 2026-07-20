import { useEffect, useState } from 'react'
import PlusIcon from "../../assets/iconzinMovie.svg?react"

import styles from './PerfilMoviesCont.module.css'



function PerfilMoviesContents({setFavAlert}){
  const [movies, setMovies] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [moviesPage, setMoviesPage] = useState(0);

  function nextPage(){
    setMoviesPage(moviesPage + 1);
  }
  function lastPage(){
    if(moviesPage > 0) setMoviesPage(moviesPage - 1);
  }

  async function addFavoriteMovie(movieId){
  
    const payload = {
      "movieId": movieId
    }
    
    const response = await fetch(
      "http://192.168.1.102:3000/user/profile/addFavMovie",{
        method: 'POST',
        headers:{
          'Authorization': `Bearer ${localStorage.getItem("token")}`,
          'content-type': 'application/json'
        },
        body: JSON.stringify(payload)
      }
    )

    const res = await response.json();
    console.log(res)
    setFavAlert(res.message);
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
        {movies ? (
          movies.map((movie) => (
            <div key={movie.id} className={styles.movieCard}>
              <img
                className={styles.moviePoster}
                src={`https://image.tmdb.org/t/p/w500${movie.posterPath}`}
                alt={movie.title}
              />

              <div className={styles.overlay}>
                <button
                  className={styles.favoriteButton}
                  onClick={() => addFavoriteMovie(movie.id)}
                >
                  <PlusIcon className={styles.plusIcon} />
                </button>

                <p>Adicionar aos favoritos</p>
              </div>

              <span className={styles.movieTitle}>
                {movie.title}
              </span>
            </div>
          ))
        ) : (
          <div>Tem nada de filmes aqui...</div>
        )}
      </div>
      
      <div className={styles.selectPages}>
        <button className={styles.pageButtons} onClick={lastPage}>&lt;</button>
        <span className={styles.pageSpan}>{moviesPage + 1}/{totalPages}</span>
        <button className={styles.pageButtons} onClick={nextPage}>&gt;</button>
      </div>
    </div>
  )

}

export default PerfilMoviesContents;