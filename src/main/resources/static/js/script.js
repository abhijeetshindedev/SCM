console.log("Script loaded");


let currenttheme = getTheme();
console.log("current theme : " + currenttheme);

// initilly change theme will call
document.addEventListener('DOMContentLoaded', () => {
    changeTheme();
});
// changeTheme();

//change theme function
function changeTheme() {
    // set to web page
    document.querySelector("html").classList.add(currenttheme);

    //set listner
    const changeThemeBtn = document.querySelector("#theme_change_btn");
    changeThemeBtn.addEventListener("click", (event) => {
        console.log("change theme btn clicked");
        const oldTheme = currenttheme;
        if(currenttheme == 'light') {
            // setTheme('dark');
            currenttheme = 'dark';
        }
        else{   
            // setTheme('light');
            currenttheme = 'light';
        }
        // update in local storage
        setTheme(currenttheme);
        // remove old theme
        document.querySelector("html").classList.remove(oldTheme);
        // add new theme
        document.querySelector("html").classList.add(currenttheme);

        //change the text of the buttom
        changeThemeBtn.querySelector("span").textContent= currenttheme == 'light' ? 'Dark' : 'Light';

    })


}

// set theme in localstorage
function setTheme(themeName) {
    localStorage.setItem('theme', themeName);
}

// get theme from localstorage
function getTheme() {
    let theme = localStorage.getItem('theme');
    return theme ? theme : "light";
}