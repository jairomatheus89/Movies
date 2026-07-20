import { useEffect, useState } from 'react';
import styles from './FavPerfil.module.css'

import Header from '../../components/header/Header'
import Footer from '../../components/footer/Footer'
import FavMoviesContents from '../../components/favMoviesCont/FavMoviesContents'

async function fetchProfileData(){

    const userData = await fetch(
        "http://192.168.1.102:3000/user/profile",{
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem("token")}`
            }
        }
    )

    const response = await userData.json();
    return response;
}

function FavPerfil(){
    const [user, setUser] = useState(null);

    useEffect(() => {
        async function callUserData(){
            const data = await fetchProfileData();
            setUser(data);

        }
        callUserData();
    }, [])

    return(
        <div className={styles.body}>
            <Header isLoginPage={false} userData={user} isFavPage={true}/>
            <h2>MY FAVORITES</h2>
            <FavMoviesContents/>
            <Footer/>
        </div>
    );
}

export default FavPerfil;