
import './Home.css'

//COMPONENTS
import Header from '../components/header/Header'
import Footer from '../components/footer/Footer'
import MoviesContents from '../components/moviesContents/MovieContents'



function Home() {

  return (
    <div className='body'>
      <Header isLoginPage={false} isFavPage={false}/>
      <MoviesContents/>
      <Footer/>
    </div>
  )
}

export default Home;