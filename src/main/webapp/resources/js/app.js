document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;
        var spanBadChoice = document.createElement('span');
        spanBadChoice.className = 'bad-data';
      var categories = [];
          $("input:checkbox[name=categories]:checked").each(function(){
            categories.push($(this));
      });

      if (this.currentStep === 2) {
        if (categories.length === 0){
            var stepOne = $("[data-step='1']")[1];
            spanBadChoice.innerText = 'Nie wybrałeś żadnej kategorii';
          stepOne.append(spanBadChoice);
          return false;
        }
      }

      var quantity = document.querySelector('#quantity').value;
      if (this.currentStep === 3) {
        if (quantity <= 0){
            var stepTwo = $("[data-step='2']")[1];
            spanBadChoice.innerText = 'Ilość oddawanych worków musi być większa od 0';
            stepTwo.append(spanBadChoice);
          return false;
        }
        if(quantity < categories.length){
          var stepTwo = $("[data-step='2']")[1];
          spanBadChoice.innerText = 'Ilość oddawanych worków jest mniejsza od ilości wybranych kategorii';
          stepTwo.append(spanBadChoice);
          return false;
        }
      }

      var institution = $("input:radio[name=institution]:checked");
      if (this.currentStep === 4) {
        if (institution.length === 0){
            var stepTree = $("[data-step='3']")[1];
            spanBadChoice.innerText = 'Komu chcesz podarować datki?';
            stepTree.append(spanBadChoice);
          return false;
        }
      }

      var city = $('#city').val();
      var street = $('#street').val();
      var zipCode = $('#zipCode').val();
      var pickUpDate = $('#pickUpDate').val(); //30-10-2019 momentJS
      var currentDate = Date.now();
      var pickUpTime = $('#pickUpTime').val();
      var pickUpComment = $('#pickUpComment').val();

        if (this.currentStep === 5) {
          if (city.length === 0 || street.length === 0 || zipCode.length === 0 || pickUpDate.length === 0 || pickUpTime.length === 0) {
              var stepFour = $("[data-step='4']")[1];
              spanBadChoice.innerText = 'Podpowiedz kurierowi jak ma odebrać datki';
              stepFour.append(spanBadChoice);
            return false;
          }
        }

      if (this.currentStep === 5) {
        if (currentDate >= Date.parse(pickUpDate)) {
          var stepFour = $("[data-step='4']")[1];
          spanBadChoice.innerText = 'Data odbioru jest z przeszłości, podaj właściwą datę';
          stepFour.append(spanBadChoice);
          return false;
        }
      }

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      var stepFive = $("[data-step='5']")[0];
      var spans = stepFive.querySelectorAll('.summary--text');
      var checkedCategories = [];
      for(var i = 0; i < categories.length; i++){
        checkedCategories += categories[i].data('category-name') + ', ';
      }

      spans[0].innerText = quantity + ' worki ' + checkedCategories;
      spans[1].innerText = 'Dla fundacji: ' + institution.data('institution-name');

      var addressList = stepFive.querySelectorAll('.form-section--column');
      var addressData = addressList[0].querySelectorAll('li');
      addressData[0].innerText = 'Ulica: ' + street;
      addressData[1].innerText = 'Miasto: ' + city;
      addressData[2].innerText = 'Kod pocztowy: ' + zipCode;

      var pickUpData = addressList[1].querySelectorAll('li');
      pickUpData[0].innerText = 'Data: ' + pickUpDate;
      pickUpData[1].innerText = 'Godzina: ' + pickUpTime;
      pickUpData[2].innerText = 'Uwagi: ' + pickUpComment;

    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
