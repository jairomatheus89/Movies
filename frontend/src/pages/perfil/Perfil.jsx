import './Perfil.css'

import Header from '../../components/header/Header'
import { useEffect, useState } from 'react';


async function fetchProfileData(){

    const userData = await fetch(
        "http://192.168.1.102:8080/user/profile",{
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

    useEffect(() => {
        async function callUserData(){
            const data = await fetchProfileData();

            setUser(data);
        }

        callUserData();
    }, [])

    return(
        <div className="body">
            <Header isLoginPage={false} userData={user}/>
            <h1>TESTOLANDIA...</h1>
        </div>
    );
}

export default Perfil;