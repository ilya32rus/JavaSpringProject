import { useMemo } from 'react';
import { useState } from 'react'
import { createData } from '../../actions/actions';
import Input from '../../components/input/input'
import './form.css'

export default function CreateRoomForm({ close }) {
    const [type, setType] = useState('');
    const [style, setStyle] = useState('');
    const [level, setLevel] = useState('');

    const memoDisabled = useMemo(() => {
        return (!type || !style || !level);
    }, [type, style, level])

    const submit = (e) => {
        e.preventDefault();
        createData('room', {
            type: type,
            style: style,
            level: level
        })
    }

    return (
        <form className="container_form" onSubmit={submit}>
            <div className="button_form">
                <button onClick={close}>Закрыть</button>
                <button type="submit" className={memoDisabled ? 'disabled' : ''} disabled={memoDisabled} >Отправить</button>
            </div>
            <Input label='Тип' change={(e) => setType(e.value)} />
            <Input label='Стиль' change={(e) => setStyle(e.value)} />
            <Input label='Этаж' change={(e) => setLevel(e.value)} />
        </form>
    )
}