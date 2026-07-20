import styles from './Perfil.module.css'

import Header from '../../components/header/Header'
import Footer from '../../components/footer/Footer'
import PerfilMoviesContents from '../../components/perfilMoviesCont/PerfilMoviesCont'
import { useEffect, useState } from 'react';

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

function Perfil(){
    const [user, setUser] = useState(null);
    const [favAlert, setFavAlert] = useState("");

    useEffect(() => {
        async function callUserData(){
            const data = await fetchProfileData();

            setUser(data);
        }
        callUserData();
    }, [favAlert])

    return(
        <div className={styles.body}>
            <Header isLoginPage={false} userData={user} isFavPage={false}/>
            <PerfilMoviesContents setFavAlert={setFavAlert}/>
            <Footer/>
            <div className={!favAlert.startsWith("ESSE FILME JA")? styles.favAlertOk : styles.favAlertFail}>{favAlert}</div>
        </div>
    );
}

export default Perfil;