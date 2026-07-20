import { useEffect, useState } from 'react';
import './Perfil.css'

import Header from '../../components/header/Header'
import Footer from '../../components/footer/Footer'

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
        <div className="body">
            <Header isLoginPage={false} userData={user} isFavPage={true}/>
            <Footer/>
        </div>
    );
}

export default FavPerfil;